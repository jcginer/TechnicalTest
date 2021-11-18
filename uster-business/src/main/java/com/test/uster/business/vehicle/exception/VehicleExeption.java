package com.test.uster.business.vehicle.exception;

/**
 * The type Vehicle exeption.
 */
public class VehicleExeption extends RuntimeException {

    /**
     * Instantiates a new Vehicle exeption.
     *
     * @param message the message
     */
    public VehicleExeption(String message) {
        super(message);
    }

    /**
     * Instantiates a new Vehicle exeption.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public VehicleExeption(String message, Throwable throwable) {
        super(message, throwable);
    }

}
