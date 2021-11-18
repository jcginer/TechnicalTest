package com.test.uster.persistence.trip.entity;

import com.test.uster.persistence.driver.entity.DriverEntity;
import com.test.uster.persistence.vehicle.entity.VehicleEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The type Trip entity.
 */
@Data
@ToString
@Entity
@Table(name = "TRIP", uniqueConstraints={
        @UniqueConstraint(columnNames={"vehicleid", "tripdate"}),
        @UniqueConstraint(columnNames={"driverid", "tripdate"})
})
public class  TripEntity {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TripEntityPK tripEntityPK;

    @ManyToOne
    @MapsId("idVehicle")
    @JoinColumn(name = "vehicleid")
    private VehicleEntity vehicleEntity;

    @ManyToOne
    @MapsId("idDriver")
    @JoinColumn(name = "driverid")
    private DriverEntity driverEntity;

}
