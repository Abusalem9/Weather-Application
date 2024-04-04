package org.freightfox.weatherreport.controller;

import org.freightfox.weatherreport.request.CreateWeatherInfo;
import org.freightfox.weatherreport.request.GetWheather;
import org.freightfox.weatherreport.response.BaseResponse;
import org.freightfox.weatherreport.service.WeatherService;
import org.freightfox.weatherreport.utils.BaseResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @PostMapping("/get")
    public ResponseEntity getWeatherInfo(@RequestBody GetWheather getWheather) {
        try {
            BaseResponse response = weatherService.getWeather(getWheather);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseUtil.createErrorBaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/fetch")
    public ResponseEntity fetchWeatherInfo(@RequestBody CreateWeatherInfo createWeatherInfo) {
        try {
            BaseResponse response = weatherService.saveOrFetchWeatherInfo(createWeatherInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseUtil.createErrorBaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
