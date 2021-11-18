package com.test.uster.business.trip.service.impl;

import com.test.uster.business.driver.service.DriverService;
import com.test.uster.business.trip.exception.TripExeption;
import com.test.uster.business.trip.mapper.TripMapper;
import com.test.uster.business.trip.service.TripService;
import com.test.uster.business.vehicle.service.VehicleService;
import com.test.uster.domain.driver.Driver;
import com.test.uster.domain.trip.Trip;
import com.test.uster.domain.trip.TripRequest;
import com.test.uster.domain.vehicle.Vehicle;
import com.test.uster.persistence.trip.entity.TripEntity;
import com.test.uster.persistence.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Trip service.
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @Override
    @Transactional
    public List<Trip> getTrips() {
        return tripRepository.findAll(Sort.by(Sort.Direction.ASC, "tripEntityPK.tripDate")).stream()
                .map(tripEntity -> TripMapper.MAPPER.map(tripEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Trip> getTripsByVehicleId(final Long idVehicle) {
        return tripRepository.findTripByVehicleIdOrderByTripDateAsc(idVehicle)
                .map(tripEntity -> TripMapper.MAPPER.map(tripEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Trip> getTripsByDriverId(final Long idDriver) {
        return tripRepository.findTripByDriverIdOrderByTripDateAsc(idDriver)
                .map(tripEntity -> TripMapper.MAPPER.map(tripEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Trip> getTripsByTripDate(final Date tripDate) {
        return tripRepository.findTripByTripDateOrderByTripDateAsc(tripDate)
                .map(tripEntity -> TripMapper.MAPPER.map(tripEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Trip createTrip(final TripRequest tripRequest) {
        validate(tripRequest);
        TripEntity tripEntity = TripMapper.MAPPER.map(getTrip(tripRequest));
        validate(tripEntity);
        return TripMapper.MAPPER.map(tripRepository.save(tripEntity));
    }

    @Override
    @Transactional
    public void deleteTrip(final TripRequest tripRequest) {
        tripRepository.delete(TripMapper.MAPPER.map(getTrip(tripRequest)));
    }

    @Override
    @Transactional
    public List<Vehicle> getAvailableVehiclesOn(final Date tripDate) {
        List<Long> vehicleIdsInUse = tripRepository.findVehiclesInUseOn(tripDate).collect(Collectors.toList());
        return vehicleService.getAvailableVehicles(vehicleIdsInUse);
    }

    @Override
    @Transactional
    public List<Driver> getAvailableDriversOn(final Date tripDate, final String licenseType) {
        validate(licenseType);
        List<Long> busyDriverIds = tripRepository.findBusyDriversOn(tripDate).collect(Collectors.toList());
        return driverService.getAvailableDrivers(busyDriverIds, licenseType);
    }

    private Trip getTrip(final TripRequest tripRequest) {
        final Vehicle vehicle = vehicleService.getVehicle(tripRequest.getVehicleId());
        final Driver driver = driverService.getDriver(tripRequest.getDriverId());
        return new Trip(vehicle, driver, tripRequest.getTripDate());
    }

    private void validate(final Date tripDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if(tripDate == null || tripDate.before(calendar.getTime())) {
            throw new TripExeption(String.format("The date is not valid: it is null or before today"));
        }
    }

    private void validate(final String licenseType) {
        if(licenseType == null || licenseType.isEmpty() || !licenseType.matches(".*[ABCDE].*")) {
            throw new TripExeption(
                    String.format("The license is not valid: should be one of 'A', 'B', 'C', 'D', 'E'"));
        }
    }

    private void validate(final TripEntity tripEntity) {
        validate(tripEntity.getTripEntityPK().getTripDate());

        if(!tripEntity.getDriverEntity().getLicense().equals(tripEntity.getVehicleEntity().getLicenseRequired())) {
            throw new TripExeption(String.format("The trip is not valid: The license of the driver has to be " +
                    "equals to the license of the vehicle"));
        }
    }

    private void validate(final TripRequest tripRequest) {
        if(tripRequest == null || tripRequest.getDriverId() == null || tripRequest.getVehicleId() == null) {
            throw new TripExeption(
                    String.format("The trip is not valid: Even the vehicle or the driver is not valid"));
        }

        validate(tripRequest.getTripDate());
    }

}
