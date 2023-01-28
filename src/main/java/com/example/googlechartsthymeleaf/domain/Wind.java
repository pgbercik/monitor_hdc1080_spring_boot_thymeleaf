
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
        "speed",
        "deg",
        "gust"
})
public class Wind {

    @JsonProperty("speed")
    public Double speed;
    @JsonProperty("deg")
    public Integer deg;
    @JsonProperty("gust")
    public Double gust;

}
