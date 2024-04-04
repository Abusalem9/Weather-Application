package org.freightfox.weatherreport.service;

import org.apache.commons.lang3.ObjectUtils;
import org.freightfox.weatherreport.entity.WeatherEntity;
import org.freightfox.weatherreport.feign.OpenWeatherClient;
import org.freightfox.weatherreport.mapper.WeatherMapper;
import org.freightfox.weatherreport.model.LocationInfo;
import org.freightfox.weatherreport.model.OpenWeatherResponse;
import org.freightfox.weatherreport.repository.WeatherRepository;
import org.freightfox.weatherreport.request.CreateWeatherInfo;
import org.freightfox.weatherreport.request.GetWheather;
import org.freightfox.weatherreport.response.BaseResponse;
import org.freightfox.weatherreport.response.WeatherResponse;
import org.freightfox.weatherreport.utils.BaseResponseUtil;
import org.freightfox.weatherreport.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private OpenWeatherClient openWeatherClient;
    @Autowired
    private WeatherMapper weatherMapper;
    @Value("${open.weather.api-key}")
    private String openWeatherApiKey;

    public BaseResponse getWeather(GetWheather getWheather) {
        try {
            // Check if the weather information for the given pincode and date is already in the database
            WeatherEntity weather = weatherRepository.getWeatherEntityByPinCodeAndDate(getWheather.getPinCode(), getWheather.getDate());
            if (ObjectUtils.isNotEmpty(weather)) {
                return BaseResponseUtil.createBaseResponse(new WeatherResponse(weather), StatusCode.OK);
            }
            return BaseResponseUtil.createBaseResponse(new WeatherResponse("No Data found."), StatusCode.NO_DATA);
        } catch (Exception e) {
            return BaseResponseUtil.createErrorBaseResponse(e.getMessage());
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public BaseResponse saveOrFetchWeatherInfo(CreateWeatherInfo createWeatherInfo) {
        try {
            WeatherEntity retrievedWeatherEntity = weatherRepository.getWeatherEntityByPinCodeAndDate(createWeatherInfo.getPinCode(), createWeatherInfo.getDate());
            if (ObjectUtils.isNotEmpty(retrievedWeatherEntity)) {
                return BaseResponseUtil.createBaseResponse(new WeatherResponse(retrievedWeatherEntity), StatusCode.OK);
            }
            WeatherEntity weatherEntity = weatherMapper.createWeatherInfoToWeatherEntity(createWeatherInfo);
            LocationInfo locationInfo = openWeatherClient.getWeatherByZipCode(createWeatherInfo.getPinCode() + "," + "IN", openWeatherApiKey);
            OpenWeatherResponse weatherByLatAndLon = openWeatherClient.getWeatherByLatAndLon(locationInfo.getLatitude(), locationInfo.getLongitude(), openWeatherApiKey);
            weatherEntity.setWeatherData(weatherByLatAndLon);
            weatherEntity.setLocationInfo(locationInfo);
            WeatherEntity savedWeatherInfo = weatherRepository.save(weatherEntity);
            return BaseResponseUtil.createBaseResponse(new WeatherResponse(savedWeatherInfo), StatusCode.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponseUtil.createErrorBaseResponse(e.getMessage());
        }
    }
}