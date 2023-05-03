package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.entity.outside_weather.TempHumTimeOnly;
import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import com.example.googlechartsthymeleaf.repository.CurrentWeatherRepo;
import com.example.googlechartsthymeleaf.repository.TemperatureRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ChartDataService {
    private final TemperatureRepo temperatureRepo;
    private final CurrentWeatherRepo currentWeatherRepo;

    public ChartDataService(TemperatureRepo temperatureRepo, CurrentWeatherRepo currentWeatherRepo) {
        this.temperatureRepo = temperatureRepo;
        this.currentWeatherRepo = currentWeatherRepo;
    }


    /**
     * We're retrieving data from database and returning in proper format (for 6h chart).
     */
    public List<List<Object>> getChartData24H() {
        return formatDataForChart(temperatureRepo.findLast24h());
    }

    /**
     * We're retrieving data from database and returning in proper format (for 6h chart).
     */
    public List<List<Object>> getChartData6H() {
        return formatDataForChart(temperatureRepo.findTop360());
    }

    public List<List<Object>> getChartData24hOutside() {
        long epoch24HoursFromNow = LocalDateTime.now()
                .minus(24, ChronoUnit.HOURS)
                .atZone(ZoneId.systemDefault())
                .toEpochSecond();
        return formatDataForChart(currentWeatherRepo.getTempHumTimeFromLast24Hours(epoch24HoursFromNow));
    }

    /**
     * We're formatting data so tht it is readable by GoogleChart.
     */
    private List<List<Object>> formatDataForChart(List<Temperature> list) {
        //list with subsequent rows containing time, temperature, humidity in each row
        List<List<Object>> targetList = new ArrayList<>();

        if (!list.isEmpty()) {
            list.forEach(temperature -> {
                String czas = String.valueOf(temperature.getCzas());
                czas = czas.substring(11, 16);
                double temp = temperature.getTemperature();
                double hum = temperature.getHumidity();

                //adding another row to the list
                targetList.add(List.of(czas, temp, hum));

            });
        }

        return targetList;
    }

    private List<List<Object>> formatDataForChart(Set<TempHumTimeOnly> list) {
        return list.stream()
                .map(t -> List.of(t.getHourMinuteFromUnixTime(), t.getTemperature(), ((Object) t.getHumidity())))
                .toList();
    }
}
