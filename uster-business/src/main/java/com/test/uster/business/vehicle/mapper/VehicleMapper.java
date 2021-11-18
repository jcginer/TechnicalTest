package com.test.uster.business.vehicle.mapper;

import com.test.uster.domain.vehicle.Vehicle;
import com.test.uster.persistence.vehicle.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Vehicle mapper.
 */
@Mapper(componentModel = "spring")
public interface VehicleMapper {

    /**
     * The constant MAPPER.
     */
    VehicleMapper MAPPER = Mappers.getMapper(VehicleMapper.class);

    /**
     * Map vehicle.
     *
     * @param vehicleEntity the vehicle entity
     * @return the vehicle
     */
    Vehicle map(VehicleEntity vehicleEntity);

    /**
     * Map vehicle entity.
     *
     * @param vehicle the vehicle
     * @return the vehicle entity
     */
    VehicleEntity map(Vehicle vehicle);
}
