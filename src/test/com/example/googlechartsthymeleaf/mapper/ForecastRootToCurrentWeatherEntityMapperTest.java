package com.example.googlechartsthymeleaf.mapper;

import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;
import com.example.googlechartsthymeleaf.json_model.ForecastRoot;
import com.example.googlechartsthymeleaf.json_model.Main;
import com.example.googlechartsthymeleaf.json_model.Sys;
import com.example.googlechartsthymeleaf.json_model.Weather;
import com.example.googlechartsthymeleaf.json_model.Wind;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ForecastRootToCurrentWeatherEntityMapperTest {

    private static ForecastRoot forecastRoot;

    @Autowired
    private ForecastRootToCurrentWeatherEntityMapper mapper;

    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/sample_response.json");
        forecastRoot = objectMapper.readValue(file, ForecastRoot.class);
    }

    @Test
    void should_map_all_fields_correctly() {
        //GIVEN-WHEN
        CurrentWeatherEntity mappedEntity = mapper.apply(forecastRoot);

        //THEN
        assertEquals(mappedEntity.getLatitude(), forecastRoot.getCoord().getLat());
        assertEquals(mappedEntity.getLongitude(), forecastRoot.getCoord().getLon());

        Weather weather = forecastRoot.getWeather().get(0);
        assertNotEquals(weather, null);
        assertEquals(mappedEntity.getWeatherConditionId(), weather.getId());
        assertEquals(mappedEntity.getWeatherDescription(), weather.getDescription());
        assertEquals(mappedEntity.getWeatherIconId(), weather.getIcon());

        Main main = forecastRoot.getMain();
        assertEquals(mappedEntity.getTemperature(), main.getTemp());
        assertEquals(mappedEntity.getPerceivedTemperature(), main.getFeelsLike());
        assertEquals(mappedEntity.getMinMeasuredTemperature(), main.getTempMin());
        assertEquals(mappedEntity.getMaxMeasuredTemperature(), main.getTempMax());
        assertEquals(mappedEntity.getHumidity(), main.getHumidity());
        assertEquals(mappedEntity.getPressureSeaLevel(), main.getPressure());
        assertEquals(mappedEntity.getPressureGroundLevel(), main.getGrndLevel());

        assertEquals(mappedEntity.getVisibility(), forecastRoot.getVisibility());

        Wind wind = forecastRoot.getWind();
        assertEquals(mappedEntity.getWindSpeed(), wind.getSpeed());
        assertEquals(mappedEntity.getWindDegree(), wind.getDeg());
        assertEquals(mappedEntity.getWindGustSpeed(), wind.getGust());

        assertEquals(mappedEntity.getMeasurementTime(), forecastRoot.getDt());

        Sys sys = forecastRoot.getSys();
        assertEquals(mappedEntity.getCountry(), sys.getCountry());
        assertEquals(mappedEntity.getSunriseTime(), sys.getSunrise());
        assertEquals(mappedEntity.getSunsetTime(), sys.getSunset());

        assertEquals(mappedEntity.getTimezoneOffset(), forecastRoot.getTimezone());
        assertEquals(mappedEntity.getCityName(), forecastRoot.getName());

    }

    @Test
    void shouldThrowExceptionWhenWeatherListIsEmpty() {
        forecastRoot.getWeather().clear();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.apply(forecastRoot));
        assertEquals(exception.getMessage(), "API returned no Weather object");
    }
}