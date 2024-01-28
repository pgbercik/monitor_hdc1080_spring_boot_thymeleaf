package com.example.googlechartsthymeleaf.json_model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootFiveDays {

    private Integer cnt;
    private ArrayList<com.example.googlechartsthymeleaf.json_model.List> list;
    private City city;



}
