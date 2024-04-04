package org.freightfox.weatherreport.mapper;

import org.freightfox.weatherreport.entity.WeatherEntity;
import org.freightfox.weatherreport.request.CreateWeatherInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherEntity createWeatherInfoToWeatherEntity(CreateWeatherInfo createWeatherInfo);
}
