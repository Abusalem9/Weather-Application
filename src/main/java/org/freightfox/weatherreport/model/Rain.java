package org.freightfox.weatherreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class Rain {
    @JsonProperty("1h")
    private double oneHour;

    
}
