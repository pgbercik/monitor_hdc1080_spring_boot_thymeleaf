package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;
import com.example.googlechartsthymeleaf.json_model.ForecastRoot;
import com.example.googlechartsthymeleaf.mapper.ForecastRootToCurrentWeatherEntityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Getter
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
    @Value("${appid}")
    private String appId;
    private static final String BASE_URL = "https://api.openweathermap.org";
    private static final String URL_PATH = "/data/2.5/weather";
    private static final String LATITUDE = "52.7867";
    private static final String LONGITUDE = "18.2609";
    private static final String UNITS = "metric";
    private static final String LANGUAGE = "pl";

    private ForecastRoot forecastRoot;
    private final ForecastRootToCurrentWeatherEntityMapper mapper;

    public ForecastRoot getWeatherForecast() {
        if (null == forecastRoot) {
            log.info("Fetching weather forecast from openweathermap API");
            forecastRoot = getWeatherFromApiFromApi();
        }
        return forecastRoot;
    }

    public CurrentWeatherEntity getCurrentWeatherEntity() {
        return mapper.apply(getWeatherForecast());
    }

    private ForecastRoot getWeatherFromApiFromApi() {
        WebClient client = WebClient.create(BASE_URL);
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(URL_PATH).queryParam("lat", LATITUDE)
                        .queryParam("lon", LONGITUDE)
                        .queryParam("appid", appId)
                        .queryParam("units", UNITS)
                        .queryParam("lang", LANGUAGE).build()
                )
                .retrieve()
                .bodyToMono(ForecastRoot.class)
                .block();
    }

}
