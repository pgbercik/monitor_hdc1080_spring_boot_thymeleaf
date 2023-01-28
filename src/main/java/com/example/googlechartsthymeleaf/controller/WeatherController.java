package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.json_model.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class WeatherController {
    @Value("${appid}")
    private String appId;

    @GetMapping("/weather")
    Root getCurrWeather() {
        WebClient client = WebClient.create("https://api.openweathermap.org");

        var result = client.get().uri(uriBuilder ->
                        {

                            return uriBuilder.path("/data/2.5/weather")
                                    .queryParam("lat", "52.7867")
                                    .queryParam("lon", "18.2609")
                                    .queryParam("appid", appId)
                                    .queryParam("units", "metric")
                                    .queryParam("lang", "pl")
                                    .build();
                        }
                )
                .retrieve()
                .bodyToMono(Root.class)
                .block();

        return result;
    }
}
