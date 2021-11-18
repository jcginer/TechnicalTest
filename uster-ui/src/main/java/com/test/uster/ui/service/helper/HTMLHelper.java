package com.test.uster.ui.service.helper;

import com.test.uster.ui.domain.Driver;
import com.test.uster.ui.domain.Trip;
import com.test.uster.ui.domain.Vehicle;

import java.util.List;

/**
 * The interface Html helper.
 */
public interface HTMLHelper {

    /**
     * Generate trips table string.
     *
     * @param trips the trips
     * @return the string
     */
    String generateTripsTable(List<Trip> trips);

    /**
     * Generate vehicles table string.
     *
     * @param vehicles the vehicles
     * @return the string
     */
    String generateVehiclesTable(List<Vehicle> vehicles);

    /**
     * Generate drivers table string.
     *
     * @param drivers the drivers
     * @return the string
     */
    String generateDriversTable(List<Driver> drivers);
}
