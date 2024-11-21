package com.example.googlechartsthymeleaf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${open-api.base-url}")
    private String webClientBaseUrl;

    @Value("${home-assistant.base-url}")
    private String homeAssistantBaseUrl;

    @Bean(name = "openApiClient")
    public WebClient getOpenApiClient() {
        return WebClient.create(webClientBaseUrl);
    }

    @Bean(name = "homeAssistantClient")
    public WebClient getHomeAssistantClient() {
        return WebClient.create(homeAssistantBaseUrl);
    }

}
