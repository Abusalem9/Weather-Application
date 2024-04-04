package org.freightfox.weatherreport.model;

import lombok.Data;

@Data
class Wind {
    private double speed;
    private int deg;
    private double gust;
}
