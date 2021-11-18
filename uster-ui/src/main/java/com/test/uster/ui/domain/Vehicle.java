package com.test.uster.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Vehicle.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle implements Serializable {

    @JsonProperty("idVehicle")
    @JsonPropertyDescription("Vehicle identifier")
    private Long idVehicle;

    @JsonProperty("brand")
    @JsonPropertyDescription("Vehicle brand")
    private String brand;

    @JsonProperty("model")
    @JsonPropertyDescription("Vehicle model")
    private String model;

    @JsonProperty("licenseRequired")
    @JsonPropertyDescription("Vehicle required license")
    private String licenseRequired;
}
