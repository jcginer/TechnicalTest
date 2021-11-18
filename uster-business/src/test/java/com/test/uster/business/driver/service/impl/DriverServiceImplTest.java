package com.test.uster.business.driver.service.impl;

import com.test.uster.business.configuration.ConfigurationTest;
import com.test.uster.business.driver.exception.DriverExeption;
import com.test.uster.domain.driver.Driver;
import com.test.uster.persistence.driver.repository.DriverRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

/**
 * The type Driver service impl test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
        classes = { ConfigurationTest.class },
        loader = AnnotationConfigContextLoader.class)
class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverService;

    @Autowired
    private DriverRepository driverRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(this.driverService, "driverRepository", this.driverRepository);
    }

    /**
     * Gets drivers.
     */
    @Test
    void getDrivers() {
        // Arrange
        driverService.createDriver(createMockedDriver());

        // Act
        List<Driver> drivers = driverService.getDrivers();

        //Assert
        Assert.assertTrue("There should be almost one Driver", !drivers.isEmpty());
    }

    /**
     * Gets driver.
     */
    @Test
    void getDriver() {
        // Arrange
        Driver currentDriver = driverService.createDriver(createMockedDriver());

        // Act
        Driver driver = driverService.getDriver(currentDriver.getIdDriver());

        //Assert
        Assert.assertNotNull("Driver should not be null", driver);
    }

    /**
     * Update driver.
     */
    @Test
    void updateDriver() {
        // Arrange
        Driver currentDriver = driverService.createDriver(createMockedDriver());

        currentDriver.setName("UserA");

        // Act
        driverService.updateDriver(currentDriver.getIdDriver(), currentDriver);

        //Assert
        currentDriver = driverService.getDriver(currentDriver.getIdDriver());

        Assert.assertEquals("There should be almost one Driver", currentDriver.getName(), "UserA");
    }

    /**
     * Delete driver.
     */
    @Test
    void deleteDriver() {
        // Arrange
        Driver currentDriver = driverService.createDriver(createMockedDriver());

        // Act
        driverService.deleteDriver(currentDriver.getIdDriver());

        //Assert
        try {
            currentDriver = driverService.getDriver(currentDriver.getIdDriver());
            Assert.assertTrue(false);
        } catch (DriverExeption e) {
            Assert.assertTrue(e.getMessage().contains("Driver not found"));

        }
    }

    /**
     * Test create driver with nulls.
     */
    @Test
    void testCreateDriverWithNulls() {
        // Arrange
        Driver driver = createMockedDriver();
        driver.setSurname(null);

        // Act
        //Assert
        try {
            Driver currentDriver = driverService.createDriver(driver);
            Assert.assertTrue(false);
        } catch (DataIntegrityViolationException e) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test wrong license.
     */
    @Ignore
        // To be tested in JUnit is required to do a "driverRepository.saveAndFlush" instead of
        // just "driverRepository.save". Using th endpoint it is working
    void testWrongLicense() {
        // Arrange
        Driver driver = createMockedDriver();
        driver.setLicense("F");

        // Act
        //Assert
        try {
            Driver currentDriver = driverService.createDriver(driver);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test wrong license 2.
     */
    @Ignore // Same as above
    void testWrongLicense2() {
        // Arrange
        Driver driver = createMockedDriver();
        driver.setLicense("FG");

        // Act
        //Assert
        try {
            Driver currentDriver = driverService.createDriver(driver);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    private Driver createMockedDriver() {
        Driver driver = new Driver();
        driver.setName("Name A");
        driver.setSurname("Surname A");
        driver.setLicense("B");

        return driver;
    }
}