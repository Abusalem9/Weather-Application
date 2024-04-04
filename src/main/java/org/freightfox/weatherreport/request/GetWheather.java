package org.freightfox.weatherreport.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetWheather {
    private String pinCode;
    private LocalDate date;
}
