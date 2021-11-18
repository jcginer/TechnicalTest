package com.test.uster.business.driver.service;

import com.test.uster.domain.driver.Driver;

import java.util.List;

/**
 * The interface Driver service.
 */
public interface DriverService {
    /**
     * Gets drivers.
     *
     * @return the drivers
     */
    List<Driver> getDrivers();

    /**
     * Gets driver.
     *
     * @param idDriver the id driver
     * @return the driver
     */
    Driver getDriver(final Long idDriver);

    /**
     * Create driver driver.
     *
     * @param driver the driver
     * @return the driver
     */
    Driver createDriver(final Driver driver);

    /**
     * Update driver.
     *
     * @param idDriver the id driver
     * @param driver   the driver
     */
    void updateDriver(final Long idDriver, final Driver driver);

    /**
     * Delete driver.
     *
     * @param idDriver the id driver
     */
    void deleteDriver(final Long idDriver);

    /**
     * Gets available drivers.
     *
     * @param busyDriverIds the busy driver ids
     * @param licenseType   the license type
     * @return the available drivers
     */
    List<Driver> getAvailableDrivers(final List<Long> busyDriverIds, final String licenseType);
}
