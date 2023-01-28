
package com.example.googlechartsthymeleaf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "temp",
        "feels_like",
        "temp_min",
        "temp_max",
        "pressure",
        "humidity",
        "sea_level",
        "grnd_level"
})
public class Main {

    @JsonProperty("temp")
    public Double temp;
    @JsonProperty("feels_like")
    public Double feelsLike;
    @JsonProperty("temp_min")
    public Double tempMin;
    @JsonProperty("temp_max")
    public Double tempMax;
    @JsonProperty("pressure")
    public Integer pressure;
    @JsonProperty("humidity")
    public Integer humidity;
    @JsonProperty("sea_level")
    public Integer seaLevel;
    @JsonProperty("grnd_level")
    public Integer grndLevel;

}
