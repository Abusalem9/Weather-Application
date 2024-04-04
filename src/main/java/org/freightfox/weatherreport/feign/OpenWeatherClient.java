package org.freightfox.weatherreport.feign;


import org.freightfox.weatherreport.model.LocationInfo;
import org.freightfox.weatherreport.model.OpenWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "open-weather-client", url = "${open.weather.url}")
public interface OpenWeatherClient {

    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

    @PostMapping("/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}")
    OpenWeatherResponse getWeatherByLatAndLon(@PathVariable Double lat, @PathVariable Double lon, @PathVariable String apiKey);

    //http://api.openweathermap.org/geo/1.0/zip?zip={zip code},{country code}&appid={API key}
    @GetMapping("/geo/1.0/zip")
    LocationInfo getWeatherByZipCode(@RequestParam String zip, @RequestParam String apiKey);
}
