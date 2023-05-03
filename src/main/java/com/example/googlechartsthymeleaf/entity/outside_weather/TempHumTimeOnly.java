package com.example.googlechartsthymeleaf.entity.outside_weather;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class TempHumTimeOnly {

    private final Double temperature;
    private final Integer humidity;
    private final Long measurementTime;

    public String getHourMinuteFromUnixTime() {
        return  LocalTime.ofInstant(Instant.ofEpochSecond(measurementTime), TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ofPattern("HH:mm"));

    }
}
