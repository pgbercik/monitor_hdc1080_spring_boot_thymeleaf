
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
        "lon",
        "lat"
})
public class Coord {

    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("lat")
    public Double lat;

}
