package com.test.uster.business.trip.exception;

/**
 * The type Trip exeption.
 */
public class TripExeption extends RuntimeException {

    /**
     * Instantiates a new Trip exeption.
     *
     * @param message the message
     */
    public TripExeption(String message) {
        super(message);
    }

    /**
     * Instantiates a new Trip exeption.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public TripExeption(String message, Throwable throwable) {
        super(message, throwable);
    }

}
