package com.example.googlechartsthymeleaf.entity.outside_weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "current_weather")
public class CurrentWeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private Integer weatherConditionId;
    private String weatherDescription;
    private String weatherIconId;
    private Double temperature;
    private Double perceivedTemperature;
    private Double minMeasuredTemperature;
    private Double maxMeasuredTemperature;
    private Integer humidity;
    private Integer pressureSeaLevel;
    private Integer pressureGroundLevel;
    private Short visibility;
    private Float windSpeed;
    private Short windDegree;
    private Float windGustSpeed;
    private Long measurementTime;
    private String country;
    private Long sunriseTime;
    private Long sunsetTime;
    private Short timezoneOffset;
    private String cityName;

}
