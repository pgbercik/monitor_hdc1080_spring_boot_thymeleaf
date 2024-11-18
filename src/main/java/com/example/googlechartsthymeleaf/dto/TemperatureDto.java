package com.example.googlechartsthymeleaf.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TemperatureDto {
    private Long id;
    private Float temperature;
    private Float humidity;
    private String dateFormatted;
}
