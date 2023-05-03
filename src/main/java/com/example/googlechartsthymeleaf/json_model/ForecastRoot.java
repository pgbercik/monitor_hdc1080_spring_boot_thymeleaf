package com.example.googlechartsthymeleaf.json_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"coord", "weather", "main", "visibility", "wind", "clouds", "dt", "sys", "timezone", "name"})
public class ForecastRoot {

    @JsonProperty("coord")
    public Coord coord;
    @JsonProperty("weather")
    public List<Weather> weather = new ArrayList<>();
    @JsonProperty("main")
    public Main main;
    @JsonProperty("visibility")
    public Short visibility;
    @JsonProperty("wind")
    public Wind wind;
    @JsonProperty("clouds")
    public Clouds clouds;
    @JsonProperty("dt")
    public Long dt;
    @JsonProperty("sys")
    public Sys sys;
    @JsonProperty("timezone")
    public Short timezone;

    @JsonProperty("name")
    public String name;

}
