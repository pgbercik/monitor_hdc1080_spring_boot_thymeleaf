package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import com.example.googlechartsthymeleaf.data_model.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataService {

    @Autowired
    private TemperatureRepo temperatureRepo;

    /**
     * Returns data for the table from DB.
     */
    public List<Temperature> getDataForTable() {
        return temperatureRepo.findTop60ByOrderByIdDesc();
    }
}
