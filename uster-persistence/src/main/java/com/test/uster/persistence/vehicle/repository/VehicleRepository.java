package com.test.uster.persistence.vehicle.repository;

import com.test.uster.persistence.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

/**
 * The interface Vehicle repository.
 */
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    /**
     * Find available vehicles stream.
     *
     * @param busyVehicleIds the busy vehicle ids
     * @return the stream
     */
    @Query("FROM VehicleEntity WHERE idVehicle NOT IN (:busyVehicleIds)")
    Stream<VehicleEntity> findAvailableVehiclesOrderByIdVehicleAsc(@Param("busyVehicleIds") List<Long> busyVehicleIds);
}
