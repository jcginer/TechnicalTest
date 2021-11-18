package com.test.uster.ui.service.helper.impl;

import com.test.uster.ui.domain.Driver;
import com.test.uster.ui.domain.Trip;
import com.test.uster.ui.domain.Vehicle;
import com.test.uster.ui.service.helper.HTMLHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Html helper.
 */
@Service
public class HTMLHelperImpl implements HTMLHelper {

    private static final String TABLE_SEPARATOR = "</td><td>";
    private static final String TABLE_INIT = "<tr><td>";
    private static final String TABLE_END = "</td></tr>";
    private static final String TABLE_CLOSE = "</table>";
    private static final String TABLE_OPEN = "<table border='1'>";
    private static final String SEPARATOR = " - ";
    private static final String TR_OPEN = "<tr>";
    private static final String TR_CLOSE = "</tr>";

    /**
     * Generate trips table string.
     *
     * @param trips the trips
     * @return the string
     */
    public String generateTripsTable(List<Trip> trips) {
        StringBuilder table = new StringBuilder();
        table.append("<h3>Trips</h3>")
                .append(TABLE_OPEN)
                .append(TR_OPEN)
                .append("<th>Trip Date</th>")
                .append("<th>Vehicle</th>")
                .append("<th>Driver</th>")
                .append("<th>License</th>")
                .append(TR_OPEN);

        trips.forEach(trip -> {
            table.append(TABLE_INIT)
                    .append(trip.getTripDate())
                    .append(TABLE_SEPARATOR)
                    .append(trip.getVehicle().getIdVehicle())
                    .append(SEPARATOR)
                    .append(trip.getVehicle().getBrand())
                    .append(SEPARATOR)
                    .append(trip.getVehicle().getModel())
                    .append(TABLE_SEPARATOR)
                    .append(trip.getDriver().getIdDriver())
                    .append(SEPARATOR)
                    .append(trip.getDriver().getName())
                    .append(SEPARATOR)
                    .append(trip.getDriver().getSurname())
                    .append(TABLE_SEPARATOR)
                    .append(trip.getDriver().getLicense())
                    .append(TABLE_END);
        });
        table.append(TABLE_CLOSE);

        return table.toString();
    }

    /**
     * Generate vehicles table string.
     *
     * @param vehicles the vehicles
     * @return the string
     */
    public String generateVehiclesTable(List<Vehicle> vehicles) {
        StringBuilder table = new StringBuilder();
        table.append("<h3>Vehicles</h3>")
                .append(TABLE_OPEN)
                .append(TR_OPEN)
                .append("<th>Id</th>")
                .append("<th>Brand</th>")
                .append("<th>Model</th>")
                .append("<th>License</th>")
                .append(TR_OPEN);

        vehicles.forEach(vehicle -> {
            table.append(TABLE_INIT)
                    .append(vehicle.getIdVehicle())
                    .append(TABLE_SEPARATOR)
                    .append(vehicle.getBrand())
                    .append(TABLE_SEPARATOR)
                    .append(vehicle.getModel())
                    .append(TABLE_SEPARATOR)
                    .append(vehicle.getLicenseRequired())
                    .append(TABLE_END);
        });
        table.append(TABLE_CLOSE);

        return table.toString();
    }

    /**
     * Generate drivers table string.
     *
     * @param drivers the drivers
     * @return the string
     */
    public String generateDriversTable(List<Driver> drivers) {
        StringBuilder table = new StringBuilder();
        table.append("<h3>Drivers</h3>")
                .append(TABLE_OPEN)
                .append(TR_OPEN)
                .append("<th>Id</th>")
                .append("<th>Name</th>")
                .append("<th>Surname</th>")
                .append("<th>License</th>")
                .append(TR_OPEN);

        drivers.forEach(driver -> {
            table.append(TABLE_INIT)
                    .append(driver.getIdDriver())
                    .append(TABLE_SEPARATOR)
                    .append(driver.getName())
                    .append(TABLE_SEPARATOR)
                    .append(driver.getSurname())
                    .append(TABLE_SEPARATOR)
                    .append(driver.getLicense())
                    .append(TABLE_END);
        });
        table.append(TABLE_CLOSE);

        return table.toString();
    }

}
