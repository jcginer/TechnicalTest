package com.test.uster.business.vehicle.service.impl;

import com.test.uster.business.vehicle.exception.VehicleExeption;
import com.test.uster.business.vehicle.mapper.VehicleMapper;
import com.test.uster.business.vehicle.service.VehicleService;
import com.test.uster.domain.vehicle.Vehicle;
import com.test.uster.persistence.vehicle.entity.VehicleEntity;
import com.test.uster.persistence.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Vehicle service.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleEntity -> VehicleMapper.MAPPER.map(vehicleEntity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Vehicle getVehicle(final Long idVehicle) {
        return VehicleMapper.MAPPER.map(
                vehicleRepository.findById(idVehicle).orElseThrow(() ->
                        new VehicleExeption(String.format("Vehicle not found: %s", idVehicle)))
        );
    }

    @Override
    @Transactional
    public Vehicle createVehicle(final Vehicle vehicle) {
        VehicleEntity vehicleEntity = VehicleMapper.MAPPER.map(vehicle);
        return VehicleMapper.MAPPER.map(vehicleRepository.save(vehicleEntity));
    }

    @Override
    @Transactional
    public void updateVehicle(final Long idVehicle, final Vehicle vehicle) {
        Vehicle currentVehicle = getVehicle(idVehicle);

        currentVehicle.setModel(vehicle.getModel());
        currentVehicle.setBrand(vehicle.getBrand());
        currentVehicle.setLicenseRequired(vehicle.getLicenseRequired());

        vehicleRepository.save(VehicleMapper.MAPPER.map(currentVehicle));
    }

    @Override
    @Transactional
    public void deleteVehicle(final Long idVehicle) {
        vehicleRepository.deleteById(idVehicle);
    }

    @Override
    @Transactional
    public List<Vehicle> getAvailableVehicles(List<Long> busyVehicleIds) {
        return vehicleRepository.findAvailableVehiclesOrderByIdVehicleAsc(busyVehicleIds)
                .map(vehicleEntity -> VehicleMapper.MAPPER.map(vehicleEntity)).collect(Collectors.toList());
    }

}
