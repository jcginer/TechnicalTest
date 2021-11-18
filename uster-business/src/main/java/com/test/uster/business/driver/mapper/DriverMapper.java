package com.test.uster.business.driver.mapper;

import com.test.uster.domain.driver.Driver;
import com.test.uster.persistence.driver.entity.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Driver mapper.
 */
@Mapper(componentModel = "spring")
public interface DriverMapper {

    /**
     * The constant MAPPER.
     */
    DriverMapper MAPPER = Mappers.getMapper(DriverMapper.class);

    /**
     * Map driver.
     *
     * @param driverEntity the driver entity
     * @return the driver
     */
    Driver map(DriverEntity driverEntity);

    /**
     * Map driver entity.
     *
     * @param driver the driver
     * @return the driver entity
     */
    DriverEntity map(Driver driver);
}
