package com.example.googlechartsthymeleaf.mapper;

import com.example.googlechartsthymeleaf.dto.CurrentWeatherDto;
import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class CurrentWeatherEntityToCurrentWeatherDtoMapper {

    public CurrentWeatherDto apply(CurrentWeatherEntity entity) {
        return CurrentWeatherDto.builder()
                .cityName(entity.getCityName())
                .weatherDescription(entity.getWeatherDescription())
                .temperature(entity.getTemperature())
                .perceivedTemperature(entity.getPerceivedTemperature())
                .humidity(entity.getHumidity())
                .pressureSeaLevel(entity.getPressureSeaLevel())
                .windSpeed(entity.getWindSpeed())
                .windGustSpeed(entity.getWindGustSpeed())
                .measurementTime(convertTime(entity.getMeasurementTime(), entity.getTimezoneOffset()))
                .country(entity.getCountry())
                .sunriseTime(convertTime(entity.getSunriseTime(), entity.getTimezoneOffset()).toLocalTime())
                .sunsetTime(convertTime(entity.getSunsetTime(), entity.getTimezoneOffset()).toLocalTime())
                .build();
    }

    /**
     * Converts epoch time to LocalDateTime
     *
     * @param epochTime epoch time in seconds
     * @param offset    time offset in seconds
     * @return LocalDateTime
     */
    private LocalDateTime convertTime(Integer epochTime, Short offset) {
        return LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.ofTotalSeconds(offset));
    }
}
