package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.domain.Root;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class WeatherController {

    @GetMapping("/weather")
    Root getCurrWeather() {
        WebClient client = WebClient.create("https://api.openweathermap.org");

        var result = client.get().uri(uriBuilder ->
                        uriBuilder.path("/data/2.5/weather")
                                .queryParam("lat", "52.7867")
                                .queryParam("lon", "18.2609")
                                .queryParam("appid", "b952190645b5d58b50b9c3bd143565e0")
                                .queryParam("units", "metric")
                                .queryParam("lang", "pl")
                                .build()
                )
                .retrieve()
                .bodyToMono(Root.class)
                .block();

        return result;
    }
}
