package com.example.googlechartsthymeleaf.repository;

import com.example.googlechartsthymeleaf.entity.outside_weather.CurrentWeatherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrentWeatherRepo extends CrudRepository<CurrentWeatherEntity, Long> {
    Optional<CurrentWeatherEntity> findFirstByOrderByIdDesc();
}
