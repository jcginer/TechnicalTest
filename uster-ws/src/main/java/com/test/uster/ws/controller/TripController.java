package com.test.uster.ws.controller;

import com.test.uster.business.trip.service.TripService;
import com.test.uster.domain.driver.Driver;
import com.test.uster.domain.trip.Trip;
import com.test.uster.domain.trip.TripRequest;
import com.test.uster.domain.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * The type Trip controller.
 */
@RestController
@RequestMapping(path = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    /**
     * Gets trips.
     *
     * @return the trips
     */
    @GetMapping
    public List<Trip> getTrips() {
        return tripService.getTrips();
    }

    /**
     * Gets trips by vehicle id.
     *
     * @param idVehicle the id vehicle
     * @return the trips by vehicle id
     */
    @GetMapping("/vehicle/{idVehicle}")
    public List<Trip> getTripsByVehicleId(@PathVariable() final Long idVehicle) {
        return tripService.getTripsByVehicleId(idVehicle);
    }

    /**
     * Gets trips by driver id.
     *
     * @param idDriver the id driver
     * @return the trips by driver id
     */
    @GetMapping("/driver/{idDriver}")
    public List<Trip> getTripsByDriverId(@PathVariable final Long idDriver) {
        return tripService.getTripsByDriverId(idDriver);
    }

    /**
     * Gets trips by trip date.
     *
     * @param tripDate the trip date
     * @return the trips by trip date
     */
    @GetMapping("/tripdate/{tripDate}")
    public List<Trip> getTripsByTripDate(@PathVariable final Date tripDate) {
        return tripService.getTripsByTripDate(tripDate);
    }

    /**
     * Create trip trip.
     *
     * @param tripRequest the trip request
     * @return the trip
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip createTrip(@RequestBody final TripRequest tripRequest) {
        return tripService.createTrip(tripRequest);
    }

    /**
     * Delete trip.
     *
     * @param tripRequest the trip request
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@RequestBody final TripRequest tripRequest) {
        tripService.deleteTrip(tripRequest);
    }

    /**
     * Gets available vehicles on.
     *
     * @param tripDate the trip date
     * @return the available vehicles on
     */
    @GetMapping("/availablevehicles")
    public List<Vehicle> getAvailableVehiclesOn(
            @RequestParam(name = "tripdate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            final Date tripDate) {
        return tripService.getAvailableVehiclesOn(tripDate);
    }

    /**
     * Gets available drivers on.
     *
     * @param tripDate    the trip date
     * @param licenseType the license type
     * @return the available drivers on
     */
    @GetMapping("/availabledrivers")
    public List<Driver> getAvailableDriversOn(@RequestParam(name = "tripdate", required = true)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date tripDate
            , @RequestParam(name = "licensetype", required = true) final String licenseType) {
        return tripService.getAvailableDriversOn(tripDate, licenseType);
    }

}
