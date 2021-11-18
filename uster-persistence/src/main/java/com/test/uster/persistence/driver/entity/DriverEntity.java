package com.test.uster.persistence.driver.entity;

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
 * The type Driver entity.
 */
@Data
@ToString
@Entity
@Table(name = "DRIVER")
@SequenceGenerator(name = DriverEntity.DRIVER_SEQ, sequenceName = DriverEntity.DRIVER_SEQ
        , initialValue = 20, allocationSize = 1)
@Check(constraints = "license IN ('A', 'B', 'C', 'D', 'E')")
public class DriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Driver seq.
     */
    static final String DRIVER_SEQ = "DRIVER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DRIVER_SEQ)
    @Column(name = "driverid")
    private Long idDriver;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "license", length = 1, nullable = false)
    private String license;

    /**
     * The Trips.
     */
    @OneToMany(mappedBy = "driverEntity")
    private Set<TripEntity> trips;
}
