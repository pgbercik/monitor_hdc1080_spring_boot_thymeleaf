package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import com.example.googlechartsthymeleaf.repository.TemperatureRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataService {

    private final TemperatureRepo temperatureRepo;

    public TableDataService(TemperatureRepo temperatureRepo) {
        this.temperatureRepo = temperatureRepo;
    }

    /**
     * Returns data for the table from DB.
     */
    public List<Temperature> getDataForTable() {
        return temperatureRepo.findTop60ByOrderByIdDesc();
    }
}
