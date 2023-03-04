package com.example.googlechartsthymeleaf.mapper;

import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;

public class CurrentWeatherEntityTestResource {

    public static CurrentWeatherEntity getCurrentWeatherEntity() {
        return CurrentWeatherEntity.builder()
                .latitude(54.1)
                .longitude(18.2)
                .weatherConditionId(3)
                .weatherDescription("weatherDescription")
                .weatherIconId("5")
                .temperature(16.0)
                .perceivedTemperature(17.0)
                .minMeasuredTemperature(12.0)
                .maxMeasuredTemperature(19.0)
                .humidity(55)
                .pressureSeaLevel(1000)
                .pressureGroundLevel(999)
                .visibility((short) 200)
                .windSpeed(15f)
                .windDegree((short) 16)
                .windGustSpeed(17f)
                .measurementTime(1676163961)
                .country("Country")
                .sunriseTime(1676182252)
                .sunsetTime(1676217102)
                .timezoneOffset((short) 3600)
                .cityName("cityName")
                .build();
    }
}
