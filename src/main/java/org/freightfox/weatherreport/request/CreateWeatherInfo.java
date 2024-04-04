package org.freightfox.weatherreport.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateWeatherInfo {
    private String pinCode;
    private LocalDate date;
    private String createdBy;
    private String createdByName;
}
