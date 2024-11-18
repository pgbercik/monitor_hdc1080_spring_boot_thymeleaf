package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.entity.outside_weather.TempHumTimeOnly;
import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import com.example.googlechartsthymeleaf.json_model.RootFiveDays;
import com.example.googlechartsthymeleaf.repository.CurrentWeatherRepo;
import com.example.googlechartsthymeleaf.repository.TemperatureRepo;
import com.example.googlechartsthymeleaf.util.TimeUtils;
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
    private final WeatherService weatherService;

    public ChartDataService(TemperatureRepo temperatureRepo, CurrentWeatherRepo currentWeatherRepo, WeatherService weatherService) {
        this.temperatureRepo = temperatureRepo;
        this.currentWeatherRepo = currentWeatherRepo;
        this.weatherService = weatherService;
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
        long epoch24HoursFromNow = LocalDateTime.now().minusHours(24)
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
                String czas = temperature.getTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES).toString();
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

    public List<List<Object>> getChartDataFiveDays() {
        RootFiveDays fiveDaysForecast = weatherService.getFiveDaysForecast();
        return fiveDaysForecast
                .getList()
                .stream()
                .map(list -> {
                    List<Object> row = new ArrayList<>();
                    row.add(TimeUtils.epochToLocalDateTime(list.getDt().longValue(), fiveDaysForecast.getCity().getTimezone()).truncatedTo(ChronoUnit.SECONDS).toString().replace("T", " "));
                    row.add(list.getMain().getTemp());
                    return row;
                })
                .toList();
    }
}
