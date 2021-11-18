package com.test.uster.persistence.trip.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Trip entity pk.
 */
@Data
@ToString
@Embeddable
public class TripEntityPK  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "vehicleid", nullable = false)
    private Long idVehicle;

    @Column(name = "driverid", nullable = false)
    private Long idDriver;

    @Column(name = "tripdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tripDate;
}
