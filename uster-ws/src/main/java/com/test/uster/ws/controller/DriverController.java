package com.test.uster.ws.controller;

import com.test.uster.business.driver.service.DriverService;
import com.test.uster.domain.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Driver controller.
 */
@RestController
@RequestMapping(path = "/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * Gets drivers.
     *
     * @return the drivers
     */
    @GetMapping
    public List<Driver> getDrivers() {
        return driverService.getDrivers();
    }

    /**
     * Gets driver.
     *
     * @param id the id
     * @return the driver
     */
    @GetMapping("/{id}")
    public Driver getDriver(@PathVariable final Long id) {
        return driverService.getDriver(id);
    }

    /**
     * Create driver driver.
     *
     * @param driver the driver
     * @return the driver
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody final Driver driver) {
        return driverService.createDriver(driver);
    }

    /**
     * Update driver.
     *
     * @param id     the id
     * @param driver the driver
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateDriver(@PathVariable final Long id, @RequestBody Driver driver) {
        driverService.updateDriver(id, driver);
    }

    /**
     * Delete driver.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable final Long id) {
        driverService.deleteDriver(id);
    }

}
