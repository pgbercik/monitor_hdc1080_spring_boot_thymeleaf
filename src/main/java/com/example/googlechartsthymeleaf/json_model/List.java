package com.example.googlechartsthymeleaf.json_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class List {
    private Integer dt;
    private Main main;
    private java.util.List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Integer visibility;
    private Integer pop;
    private Sys sys;

    @JsonProperty("dtTxt")
    private String dtTxt;
}
