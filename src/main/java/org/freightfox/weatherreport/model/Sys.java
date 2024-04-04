package org.freightfox.weatherreport.model;

import lombok.Data;

@Data
class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;

    
}
