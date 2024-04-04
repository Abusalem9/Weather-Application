package org.freightfox.weatherreport.repository;

import org.freightfox.weatherreport.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {
    WeatherEntity getWeatherEntityByPinCodeAndDate(String pinCode, LocalDate date);
}
