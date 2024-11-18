package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.dto.TemperatureDto;
import com.example.googlechartsthymeleaf.repository.TemperatureRepo;
import com.example.googlechartsthymeleaf.util.TimeUtils;
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
    public List<TemperatureDto> getDataForTable() {
        return temperatureRepo.findTop60ByOrderByIdDesc().stream()
                .map(t -> TemperatureDto.builder()
                        .id(t.getId())
                        .temperature(t.getTemperature())
                        .humidity(t.getHumidity())
                        .dateFormatted(TimeUtils.localDateTimeToString(t.getTime(), TimeUtils.LOCAL_DATE_TIME_FORMATTER))
                        .build())
                .toList();
    }
}
