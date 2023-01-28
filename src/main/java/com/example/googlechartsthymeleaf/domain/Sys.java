
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
        "type",
        "id",
        "country",
        "sunrise",
        "sunset"
})
public class Sys {

    @JsonProperty("type")
    public Integer type;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("country")
    public String country;
    @JsonProperty("sunrise")
    public Integer sunrise;
    @JsonProperty("sunset")
    public Integer sunset;

}
