package com.example.googlechartsthymeleaf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.ZonedDateTime;

@SuppressWarnings("unused")
@Getter
public class SensorState {

    @JsonProperty("entity-id")
    private String entityId;

    private Float state;

    @JsonProperty("last_changed")
    private ZonedDateTime lastChanged;

    @JsonProperty("last_reported")
    private ZonedDateTime lastReported;

    @JsonProperty("last_updated")
    private ZonedDateTime lastUpdated;
}
