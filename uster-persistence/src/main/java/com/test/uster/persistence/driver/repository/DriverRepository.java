package com.test.uster.persistence.driver.repository;

import com.test.uster.persistence.driver.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

/**
 * The interface Driver repository.
 */
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    /**
     * Find available drivers stream.
     *
     * @param busyDriverIds the busy driver ids
     * @param licenseType   the license type
     * @return the stream
     */
    @Query("FROM DriverEntity WHERE idDriver NOT IN (:busyDriverIds) AND license = :licenseType")
    Stream<DriverEntity> findAvailableDriversOrderByIdDriverAsc(
            @Param("busyDriverIds") List<Long> busyDriverIds, @Param("licenseType") final String licenseType);
}
