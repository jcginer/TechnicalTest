package com.test.uster.business.trip.service;

import com.test.uster.domain.driver.Driver;
import com.test.uster.domain.trip.Trip;
import com.test.uster.domain.trip.TripRequest;
import com.test.uster.domain.vehicle.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * The interface Trip service.
 */
public interface TripService {
    /**
     * Gets trips.
     *
     * @return the trips
     */
    List<Trip> getTrips();

    /**
     * Gets trips by vehicle id.
     *
     * @param idVehicle the id vehicle
     * @return the trips by vehicle id
     */
    List<Trip> getTripsByVehicleId(final Long idVehicle);

    /**
     * Gets trips by driver id.
     *
     * @param idDriver the id driver
     * @return the trips by driver id
     */
    List<Trip> getTripsByDriverId(final Long idDriver);

    /**
     * Gets trips by trip date.
     *
     * @param tripDate the trip date
     * @return the trips by trip date
     */
    List<Trip> getTripsByTripDate(final Date tripDate);

    /**
     * Create trip trip.
     *
     * @param trip the trip
     * @return the trip
     */
    Trip createTrip(final TripRequest trip);

    /**
     * Delete trip.
     *
     * @param trip the trip
     */
    void deleteTrip(final TripRequest trip);

    /**
     * Gets available vehicles on.
     *
     * @param tripDate the trip date
     * @return the available vehicles on
     */
    List<Vehicle> getAvailableVehiclesOn(final Date tripDate);

    /**
     * Gets available drivers on.
     *
     * @param tripDate    the trip date
     * @param licenseType the license type
     * @return the available drivers on
     */
    List<Driver> getAvailableDriversOn(final Date tripDate, final String licenseType);
}
