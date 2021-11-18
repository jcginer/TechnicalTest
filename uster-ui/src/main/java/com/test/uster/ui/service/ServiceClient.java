package com.test.uster.ui.service;

import com.test.uster.ui.domain.Driver;
import com.test.uster.ui.domain.Trip;
import com.test.uster.ui.domain.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * The interface Service client.
 */
public interface ServiceClient {
    /**
     * Gets all trips.
     *
     * @return the all trips
     */
    List<Trip> getAllTrips();

    /**
     * Create trip trip.
     *
     * @param vehicleId the vehicle id
     * @param driverId  the driver id
     * @param tripDate  the trip date
     * @return the trip
     */
    Trip createTrip(Long vehicleId, Long driverId, Date tripDate);

    /**
     * Gets all vehicles.
     *
     * @return the all vehicles
     */
    List<Vehicle> getAllVehicles();

    /**
     * Gets vehicle by id.
     *
     * @param vehicleId the vehicle id
     * @return the vehicle by id
     */
    Vehicle getVehicleById(String vehicleId);

    /**
     * Gets available vehicles.
     *
     * @param tripDate the trip date
     * @return the available vehicles
     */
    List<Vehicle> getAvailableVehicles(final String tripDate);

    /**
     * Gets available drivers.
     *
     * @param tripDate  the trip date
     * @param idVehicle the id vehicle
     * @return the available drivers
     */
    List<Driver> getAvailableDrivers(final String tripDate, final String idVehicle);

    /**
     * Create vehicle vehicle.
     *
     * @param branch          the branch
     * @param model           the model
     * @param licenseRequired the license required
     * @return the vehicle
     */
    Vehicle createVehicle(String branch, String model, String licenseRequired);

    /**
     * Gets all drivers.
     *
     * @return the all drivers
     */
    List<Driver> getAllDrivers();

    /**
     * Create driver driver.
     *
     * @param name    the name
     * @param surname the surname
     * @param license the license
     * @return the driver
     */
    Driver createDriver(String name, String surname, String license);

}
