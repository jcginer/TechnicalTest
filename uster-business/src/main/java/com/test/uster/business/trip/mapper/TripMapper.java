package com.test.uster.business.trip.mapper;

import com.test.uster.domain.trip.Trip;
import com.test.uster.persistence.trip.entity.TripEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The interface Trip mapper.
 */
@Mapper(componentModel = "spring")
public interface TripMapper {

    /**
     * The constant MAPPER.
     */
    TripMapper MAPPER = Mappers.getMapper(TripMapper.class);

    /**
     * Map trip.
     *
     * @param tripEntity the trip entity
     * @return the trip
     */
    @Mapping(source = "vehicleEntity", target = "vehicle")
    @Mapping(source = "driverEntity", target = "driver")
    @Mapping(source = "tripEntityPK.tripDate", target = "tripDate")
    Trip map(TripEntity tripEntity);

    /**
     * Map trip entity.
     *
     * @param trip the trip
     * @return the trip entity
     */
    @Mapping(source = "vehicle", target = "vehicleEntity")
    @Mapping(source = "driver", target = "driverEntity")
    @Mapping(source = "vehicle.idVehicle", target = "tripEntityPK.idVehicle")
    @Mapping(source = "driver.idDriver", target = "tripEntityPK.idDriver")
    @Mapping(source = "tripDate", target = "tripEntityPK.tripDate")
    TripEntity map(Trip trip);
}
