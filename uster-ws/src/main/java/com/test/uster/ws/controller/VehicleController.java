package com.test.uster.ws.controller;

import com.test.uster.business.vehicle.service.VehicleService;
import com.test.uster.domain.vehicle.Vehicle;
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
 * The type Vehicle controller.
 */
@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    /**
     * Get vehicle vehicle.
     *
     * @param id the id
     * @return the vehicle
     */
    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable final Long id){
        return vehicleService.getVehicle(id);
    }

    /**
     * Create vehicle vehicle.
     *
     * @param vehicle the vehicle
     * @return the vehicle
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle createVehicle(@RequestBody final Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    /**
     * Update vehicle.
     *
     * @param id      the id
     * @param vehicle the vehicle
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateVehicle(@PathVariable final Long id, @RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(id, vehicle);
    }

    /**
     * Delete vehicle.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable final Long id) {
        vehicleService.deleteVehicle(id);
    }

}
