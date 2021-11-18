package com.test.uster.business.driver.exception;

/**
 * The type Driver exeption.
 */
public class DriverExeption extends RuntimeException {

    /**
     * Instantiates a new Driver exeption.
     *
     * @param message the message
     */
    public DriverExeption(String message) {
        super(message);
    }

    /**
     * Instantiates a new Driver exeption.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public DriverExeption(String message, Throwable throwable) {
        super(message, throwable);
    }

}
