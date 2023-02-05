package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;
import com.example.googlechartsthymeleaf.json_model.ForecastRoot;
import com.example.googlechartsthymeleaf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    ForecastRoot getCurrWeather() {
        return weatherService.getWeatherForecast();
    }

    @GetMapping("/weather_entity")
    CurrentWeatherEntity getCurrWeatherEntity() {
        return weatherService.getCurrentWeatherEntity();
    }


}
