package com.example.googlechartsthymeleaf.json_model;

import com.example.googlechartsthymeleaf.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class City {
    private Integer id;
    private String name;
    private Coord coord;
    private String country;
    private Integer population;
    private Short timezone;

    @Getter(AccessLevel.NONE)
    private Long sunrise;

    @Getter(AccessLevel.NONE)
    private Long sunset;

    public LocalTime getSunriseTime() {
        return TimeUtils.epochToLocalDateTime(sunrise, timezone).toLocalTime();
    }

    public LocalTime getSunset() {
        return TimeUtils.epochToLocalDateTime(sunset, timezone).toLocalTime();
    }
}
