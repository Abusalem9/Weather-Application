package org.freightfox.weatherreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherReportApplication.class, args);
    }

}
