
package com.example.googlechartsthymeleaf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "visibility",
        "wind",
        "clouds",
        "dt",
        "sys",
        "timezone",
        "id",
        "name",
        "cod"
})
public class Root {

    @JsonProperty("coord")
    public Coord coord;
    @JsonProperty("weather")
    public List<Weather> weather = null;
    @JsonProperty("base")
    public String base;
    @JsonProperty("main")
    public Main main;
    @JsonProperty("visibility")
    public Integer visibility;
    @JsonProperty("wind")
    public Wind wind;
    @JsonProperty("clouds")
    public Clouds clouds;
    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("sys")
    public Sys sys;
    @JsonProperty("timezone")
    public Integer timezone;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("cod")
    public Integer cod;

}
