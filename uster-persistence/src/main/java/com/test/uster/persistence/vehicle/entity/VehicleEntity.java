package com.test.uster.persistence.vehicle.entity;

import com.test.uster.persistence.trip.entity.TripEntity;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * The type Vehicle entity.
 */
@Data
@ToString
@Entity
@Table(name = "VEHICLE")
@SequenceGenerator(name = VehicleEntity.VEHICLE_SEQ, sequenceName = VehicleEntity.VEHICLE_SEQ
        , initialValue = 20, allocationSize = 1)
@Check(constraints = "licenserequired IN ('A', 'B', 'C', 'D', 'E')")
public class VehicleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Vehicle seq.
     */
    static final String VEHICLE_SEQ = "VEHICLE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = VEHICLE_SEQ)
    @Column(name = "vehicleid")
    private Long idVehicle;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "licenserequired", length = 1, nullable = false)
    private String licenseRequired;

    @OneToMany(mappedBy = "vehicleEntity")
    private Set<TripEntity> trips;
}
