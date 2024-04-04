package org.freightfox.weatherreport.model;

import lombok.Data;

@Data
class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    
}
