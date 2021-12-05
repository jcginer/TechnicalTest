package com.test.uster.business.trip.service.impl;

import com.test.uster.business.configuration.ConfigurationTest;
import com.test.uster.business.driver.service.DriverService;
import com.test.uster.business.trip.exception.TripExeption;
import com.test.uster.business.vehicle.service.VehicleService;
import com.test.uster.domain.driver.Driver;
import com.test.uster.domain.trip.Trip;
import com.test.uster.domain.trip.TripRequest;
import com.test.uster.domain.vehicle.Vehicle;
import com.test.uster.persistence.driver.repository.DriverRepository;
import com.test.uster.persistence.trip.repository.TripRepository;
import com.test.uster.persistence.vehicle.repository.VehicleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The type Trip service impl test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
        classes = { ConfigurationTest.class },
        loader = AnnotationConfigContextLoader.class)
class TripServiceImplTest {

    @InjectMocks
    private TripServiceImpl tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(this.tripService, "tripRepository", this.tripRepository);
        ReflectionTestUtils.setField(this.tripService, "vehicleService", this.vehicleService);
        ReflectionTestUtils.setField(this.vehicleService, "vehicleRepository", this.vehicleRepository);
        ReflectionTestUtils.setField(this.tripService, "driverService", this.driverService);
        ReflectionTestUtils.setField(this.driverService, "driverRepository", this.driverRepository);
    }

    /**
     * Gets trips.
     *
     * @throws ParseException the parse exception
     */
    @Test
    void getTrips() throws ParseException {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());
        Driver currentDriver = driverService.createDriver(createMockedDriver());
        tripService.createTrip(createMockedTrip(currentVehicle.getIdVehicle(), currentDriver.getIdDriver()));

        // Act
        List<Trip> trips = tripService.getTrips();

        //Assert
        Assert.assertTrue("There should be almost one Trip", !trips.isEmpty());
    }

    /**
     * Delete trip.
     *
     * @throws ParseException the parse exception
     */
    @Test
    void deleteTrip() throws ParseException {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());
        Driver currentDriver = driverService.createDriver(createMockedDriver());
        tripService.createTrip(createMockedTrip(currentVehicle.getIdVehicle(), currentDriver.getIdDriver()));
        TripRequest currentTripRequest = createMockedTrip(currentVehicle.getIdVehicle(), currentDriver.getIdDriver());

        // Act
        tripService.deleteTrip(currentTripRequest);

        //Assert
        Assert.assertTrue(true);
    }

    /**
     * Test create trip with nulls.
     *
     * @throws ParseException the parse exception
     */
    @Test
    void testCreateTripWithNulls() throws ParseException {
        // Arrange
        Vehicle currentVehicle = vehicleService.createVehicle(createMockedVehicle());
        Driver currentDriver = driverService.createDriver(createMockedDriver());
        tripService.createTrip(createMockedTrip(currentVehicle.getIdVehicle(), currentDriver.getIdDriver()));
        TripRequest tripRequest = createMockedTrip(currentVehicle.getIdVehicle(), null);

        // Act
        //Assert
        try {
            Trip currentTrip = tripService.createTrip(tripRequest);
            Assert.assertTrue(false);
        } catch (TripExeption e) {
            Assert.assertTrue(true);
        }
    }

    private TripRequest createMockedTrip(final Long vehicleId, final Long driverId) throws ParseException {
        return new TripRequest(vehicleId, driverId, new Date());
    }

    private Vehicle createMockedVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand A");
        vehicle.setModel("Model A");
        vehicle.setLicenseRequired("B");

        return vehicle;
    }

    private Driver createMockedDriver() {
        Driver driver = new Driver();
        driver.setName("Name A");
        driver.setSurname("Surname A");
        driver.setLicense("B");

        return driver;
    }

}