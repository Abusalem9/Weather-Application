package org.freightfox.weatherreport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.freightfox.weatherreport.request.CreateWeatherInfo;
import org.freightfox.weatherreport.request.GetWheather;
import org.freightfox.weatherreport.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getWeather() throws Exception {
        GetWheather getWeather = new GetWheather();
        getWeather.setPinCode("123456");
        getWeather.setDate(LocalDate.parse("2022-12-12"));

        when(weatherService.getWeather(getWeather)).thenReturn(null);

        mockMvc.perform(post("/api/weather/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getWeather)))
                .andExpect(status().isOk());
    }

    @Test
    void saveOrFetchWeatherInfo() throws Exception {
        CreateWeatherInfo createWeatherInfo = new CreateWeatherInfo();
        createWeatherInfo.setPinCode("123456");
        createWeatherInfo.setDate(LocalDate.parse("2022-12-12"));

        when(weatherService.saveOrFetchWeatherInfo(createWeatherInfo)).thenReturn(null);

        mockMvc.perform(post("/api/weather/fetch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWeatherInfo)))
                .andExpect(status().isOk());
    }
}