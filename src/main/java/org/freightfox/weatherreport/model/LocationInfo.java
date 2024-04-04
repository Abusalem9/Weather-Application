package org.freightfox.weatherreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationInfo {

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

    @JsonProperty("country")
    private String country;
}