package com.test.uster.domain.trip;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.test.uster.domain.driver.Driver;
import com.test.uster.domain.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Trip.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip {

    @JsonProperty("vehicle")
    @JsonPropertyDescription("Vehicle object")
    private Vehicle vehicle;

    @JsonProperty("driver")
    @JsonPropertyDescription("Driver object")
    private Driver driver;

    @JsonProperty("tripDate")
    @JsonPropertyDescription("Trip date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = JsonFormat.DEFAULT_LOCALE
            , timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Date tripDate;

}
