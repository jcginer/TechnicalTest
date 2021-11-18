package com.test.uster.business.vehicle.service;

import com.test.uster.domain.vehicle.Vehicle;

import java.util.List;

/**
 * The interface Vehicle service.
 */
public interface VehicleService {
    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    List<Vehicle> getVehicles();

    /**
     * Gets vehicle.
     *
     * @param idVehicle the id vehicle
     * @return the vehicle
     */
    Vehicle getVehicle(final Long idVehicle);

    /**
     * Create vehicle vehicle.
     *
     * @param vehicle the vehicle
     * @return the vehicle
     */
    Vehicle createVehicle(final Vehicle vehicle);

    /**
     * Update vehicle.
     *
     * @param idVehicle the id vehicle
     * @param vehicle   the vehicle
     */
    void updateVehicle(final Long idVehicle, final Vehicle vehicle);

    /**
     * Delete vehicle.
     *
     * @param idVehicle the id vehicle
     */
    void deleteVehicle(final Long idVehicle);

    /**
     * Gets available vehicles.
     *
     * @param busyVehicleIds the busy vehicle ids
     * @return the available vehicles
     */
    List<Vehicle> getAvailableVehicles(List<Long> busyVehicleIds);
}
