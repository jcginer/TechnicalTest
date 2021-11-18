package com.test.uster.domain.trip;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Trip request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripRequest {

    @JsonProperty("vehicle")
    @JsonPropertyDescription("Vehicle identifier")
    private Long vehicleId;

    @JsonProperty("driver")
    @JsonPropertyDescription("Driver identifier")
    private Long driverId;

    @JsonProperty("tripDate")
    @JsonPropertyDescription("Trip date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = JsonFormat.DEFAULT_LOCALE
            , timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Date tripDate;
}