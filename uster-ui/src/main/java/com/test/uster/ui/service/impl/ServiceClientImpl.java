package com.test.uster.ui.service.impl;

import com.test.uster.ui.domain.Driver;
import com.test.uster.ui.domain.Trip;
import com.test.uster.ui.domain.TripRequest;
import com.test.uster.ui.domain.Vehicle;
import com.test.uster.ui.service.ServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Service client.
 */
@Service
public class ServiceClientImpl implements ServiceClient {

    private static final String URL_BASIC = "http://localhost:9101";
    private static final String URL_TRIPS = URL_BASIC +  "/trips";
    private static final String TRIPDATE_TAG = ":tripdate";
    private static final String LICENSE_TYPE_TAG = ":licensetype";
    private static final String URL_AVAILABLE_VEHICLES = URL_TRIPS + "/availablevehicles?tripdate=" + TRIPDATE_TAG;
    private static final String URL_AVAILABLE_DRIVERS = URL_TRIPS + "/availabledrivers?tripdate=" + TRIPDATE_TAG
            + "&licensetype=" + LICENSE_TYPE_TAG;
    private static final String URL_VEHICLES = URL_BASIC +  "/vehicles";
    private static final String URL_DRIVERS = URL_BASIC +  "/drivers";

    private final RestTemplate restTemplate;

    /**
     * Instantiates a new Service client.
     *
     * @param restTemplateBuilder the rest template builder
     */
    public ServiceClientImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * Gets all trips.
     *
     * @return the all trips
     */
    @Override
    public List<Trip> getAllTrips() {
        return Arrays.asList(restTemplate.getForObject(URL_TRIPS, Trip[].class));
    }

    /**
     * Create trip trip.
     *
     * @param vehicleId the vehicle id
     * @param driverId  the driver id
     * @param tripDate  the trip date
     * @return the trip
     */
    @Override
    public Trip createTrip(Long vehicleId, Long driverId, Date tripDate) {
        TripRequest tripRequest = new TripRequest(vehicleId, driverId, tripDate);
        final HttpEntity<TripRequest> httpEntity = new HttpEntity<>(tripRequest);
        ResponseEntity<Trip> responseEntity = null;
        responseEntity = restTemplate.exchange(URL_TRIPS, HttpMethod.POST, httpEntity, Trip.class);
        return responseEntity.getBody();
    }

    /**
     * Gets all vehicles.
     *
     * @return the all vehicles
     */
    @Override
    public List<Vehicle> getAllVehicles() {
        return Arrays.asList(restTemplate.getForObject(URL_VEHICLES, Vehicle[].class));
    }

    /**
     * Gets vehicle by id.
     *
     * @param vehicleId the vehicle id
     * @return the vehicle by id
     */
    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return restTemplate.getForObject(URL_VEHICLES + "/" + vehicleId, Vehicle.class);
    }

    /**
     * Gets available vehicles.
     *
     * @param tripDate the trip date
     * @return the available vehicles
     */
    @Override
    public List<Vehicle> getAvailableVehicles(final String tripDate) {
        String url = URL_AVAILABLE_VEHICLES.replace(TRIPDATE_TAG, tripDate);
        return Arrays.asList(restTemplate.getForObject(url, Vehicle[].class));
    }

    /**
     * Gets available drivers.
     *
     * @param tripDate  the trip date
     * @param idVehicle the id vehicle
     * @return the available drivers
     */
    @Override
    public List<Driver> getAvailableDrivers(final String tripDate, final String idVehicle) {
        Vehicle vehicle = getVehicleById(idVehicle);
        String url = URL_AVAILABLE_DRIVERS.replace(TRIPDATE_TAG, tripDate)
                .replace(LICENSE_TYPE_TAG, vehicle.getLicenseRequired());
        return Arrays.asList(restTemplate.getForObject(url, Driver[].class));
    }

    /**
     * Create vehicle vehicle.
     *
     * @param branch          the branch
     * @param model           the model
     * @param licenseRequired the license required
     * @return the vehicle
     */
    @Override
    public Vehicle createVehicle(String branch, String model, String licenseRequired) {
        Vehicle vehicleRequest = new Vehicle(null, branch, model, licenseRequired);
        final HttpEntity<Vehicle> httpEntity = new HttpEntity<>(vehicleRequest);
        ResponseEntity<Vehicle> responseEntity = null;
        responseEntity = restTemplate.exchange(URL_VEHICLES, HttpMethod.POST, httpEntity, Vehicle.class);
        return responseEntity.getBody();
    }

    /**
     * Gets all drivers.
     *
     * @return the all drivers
     */
    @Override
    public List<Driver> getAllDrivers() {
        return Arrays.asList(restTemplate.getForObject(URL_DRIVERS, Driver[].class));
    }

    /**
     * Create driver driver.
     *
     * @param name    the name
     * @param surname the surname
     * @param license the license
     * @return the driver
     */
    @Override
    public Driver createDriver(String name, String surname, String license) {
        Driver driverRequest = new Driver(null, name, surname, license);
        final HttpEntity<Driver> httpEntity = new HttpEntity<>(driverRequest);
        ResponseEntity<Driver> responseEntity = null;
        responseEntity = restTemplate.exchange(URL_DRIVERS, HttpMethod.POST, httpEntity, Driver.class);
        return responseEntity.getBody();
    }

}
