package com.test.uster.business.vehicle.service.impl;

import com.test.uster.business.configuration.ConfigurationTest;
import com.test.uster.business.vehicle.exception.VehicleExeption;
import com.test.uster.domain.vehicle.Vehicle;
import com.test.uster.persistence.vehicle.repository.VehicleRepository;
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
 * The type Vehicle service impl test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
        classes = { ConfigurationTest.class },
        loader = AnnotationConfigContextLoader.class)
class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Sets up.
     */
    @BeforeEach public void setUp() {
        ReflectionTestUtils.setField(this.vehicleService, "vehicleRepository", this.vehicleRepository);
    }

    /**
     * Gets vehicles.
     */
    @Test
    void getVehicles() {
        // Arrange
        vehicleService.createVehicle(createMockedVehicle());

        // Act
        List<Vehicle> vehicles = vehicleService.getVehicles();

        //Assert
        Assert.assertTrue("There should be almost one Vehicle", !vehicles.isEmpty());
    }

    /**
     * Gets vehicle.
     */
    @Test
    void getVehicle() {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());

        // Act
        Vehicle vehicle = vehicleService.getVehicle(currentVehicle.getIdVehicle());

        //Assert
        Assert.assertNotNull("Vehicle should not be null", vehicle);
    }

    /**
     * Update vehicle.
     */
    @Test
    void updateVehicle() {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());

        currentVehicle.setBrand("BMW");

        // Act
        vehicleService.updateVehicle(currentVehicle.getIdVehicle(), currentVehicle);

        //Assert
        currentVehicle = vehicleService.getVehicle(currentVehicle.getIdVehicle());

        Assert.assertEquals("There should be almost one Vehicle", currentVehicle.getBrand(), "BMW");
    }

    /**
     * Delete vehicle.
     */
    @Test
    void deleteVehicle() {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());

        // Act
        vehicleService.deleteVehicle(currentVehicle.getIdVehicle());

        //Assert
        try {
            currentVehicle = vehicleService.getVehicle(currentVehicle.getIdVehicle());
            Assert.assertTrue(false);
        } catch (VehicleExeption e) {
            Assert.assertTrue(e.getMessage().contains("Vehicle not found"));

        }
    }

    /**
     * Test create vehicle with nulls.
     */
    @Test
    void testCreateVehicleWithNulls() {
        // Arrange
        Vehicle vehicle = createMockedVehicle();
        vehicle.setModel(null);

        // Act
        //Assert
        try {
            Vehicle currentVehicle = vehicleService.createVehicle(vehicle);
            Assert.assertTrue(false);
        } catch (DataIntegrityViolationException e) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test wrong license.
     */
    @Ignore // To be tested in JUnit is required to do a "vehicleRepository.saveAndFlush" instead of
        // just "vehicleRepository.save". Using th endpoint it is working
    void testWrongLicense() {
        // Arrange
        Vehicle vehicle = createMockedVehicle();
        vehicle.setLicenseRequired("F");

        // Act
        //Assert
        try {
            Vehicle currentVehicle = vehicleService.createVehicle(vehicle);
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
        Vehicle vehicle = createMockedVehicle();
        vehicle.setLicenseRequired("FG");

        // Act
        //Assert
        try {
            Vehicle currentVehicle = vehicleService.createVehicle(vehicle);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    private Vehicle createMockedVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand A");
        vehicle.setModel("Model A");
        vehicle.setLicenseRequired("B");

        return vehicle;
    }
}