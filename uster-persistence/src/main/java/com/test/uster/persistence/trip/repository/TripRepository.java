package com.test.uster.persistence.trip.repository;

import com.test.uster.persistence.trip.entity.TripEntity;
import com.test.uster.persistence.trip.entity.TripEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.stream.Stream;

/**
 * The interface Trip repository.
 */
public interface TripRepository extends JpaRepository<TripEntity, TripEntityPK> {

    /**
     * Find trip by vehicle id stream.
     *
     * @param vehicleId the vehicle id
     * @return the stream
     */
    @Query("FROM TripEntity WHERE vehicleEntity.idVehicle = :vehicleId")
    Stream<TripEntity> findTripByVehicleIdOrderByTripDateAsc(@Param("vehicleId") Long vehicleId);

    /**
     * Find trip by driver id stream.
     *
     * @param vehicleId the vehicle id
     * @return the stream
     */
    @Query("FROM TripEntity WHERE driverEntity.idDriver = :driverId")
    Stream<TripEntity> findTripByDriverIdOrderByTripDateAsc(@Param("driverId") Long vehicleId);

    /**
     * Find trip by trip date stream.
     *
     * @param tripDate the trip date
     * @return the stream
     */
    @Query("FROM TripEntity WHERE tripEntityPK.tripDate = :tripDate")
    Stream<TripEntity> findTripByTripDateOrderByTripDateAsc(@Param("tripDate") Date tripDate);

    /**
     * Find vehicles in use on stream.
     *
     * @param tripDate the trip date
     * @return the stream
     */
    @Query("SELECT tripEntityPK.idVehicle FROM TripEntity WHERE tripEntityPK.tripDate = :tripDate")
    Stream<Long> findVehiclesInUseOn(@Param("tripDate") Date tripDate);

    /**
     * Find busy drivers on stream.
     *
     * @param tripDate the trip date
     * @return the stream
     */
    @Query("SELECT tripEntityPK.idDriver FROM TripEntity WHERE tripEntityPK.tripDate = :tripDate")
    Stream<Long> findBusyDriversOn(@Param("tripDate") Date tripDate);
}
