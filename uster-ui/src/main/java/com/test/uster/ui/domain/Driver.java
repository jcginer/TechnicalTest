package com.test.uster.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Driver.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver implements Serializable {

    @JsonProperty("idDriver")
    @JsonPropertyDescription("Driver identifier")
    private Long idDriver;

    @JsonProperty("name")
    @JsonPropertyDescription("Driver name")
    private String name;

    @JsonProperty("surname")
    @JsonPropertyDescription("Driver surname")
    private String surname;

    @JsonProperty("license")
    @JsonPropertyDescription("Driver driving license")
    private String license;
}
