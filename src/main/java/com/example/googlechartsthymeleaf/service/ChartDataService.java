package com.example.googlechartsthymeleaf.service;

import com.example.googlechartsthymeleaf.dto.SensorState;
import com.example.googlechartsthymeleaf.entity.outside_weather.TempHumTimeOnly;
import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import com.example.googlechartsthymeleaf.exception.WrongAnswerFromApiException;
import com.example.googlechartsthymeleaf.json_model.RootFiveDays;
import com.example.googlechartsthymeleaf.repository.CurrentWeatherRepo;
import com.example.googlechartsthymeleaf.repository.TemperatureRepo;
import com.example.googlechartsthymeleaf.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChartDataService {

    @Value("${home-assistant.temperature-endpoint}")
    private String temperatureEndpoint;

    @Value("${home-assistant.humidity-endpoint}")
    private String humidityEndpoint;

    @Value("${home-assistant.auth-token}")
    private String homeAssistantToken;

    private final TemperatureRepo temperatureRepo;
    private final CurrentWeatherRepo currentWeatherRepo;
    private final WeatherService weatherService;
    private final WebClient homeAssistantClient;


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
                .map(t -> List.of(t.getHourMinuteFromUnixTime(), t.temperature(), ((Object) t.humidity())))
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

    @Scheduled(cron = "0 */1 * * * *")
    private void getTempAndHumidityFromHomeAssistant() {
        SensorState temp = getSensorState(temperatureEndpoint);
        SensorState hum = getSensorState(humidityEndpoint);

        if (temp == null) {
            throw new WrongAnswerFromApiException("Null temperature returned from Home Assistant");
        }
        if (hum == null) {
            throw new WrongAnswerFromApiException("Null humidity returned from Home Assistant");
        }

        LocalDateTime sensorLastUpdate = temp.getLastReported()
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);

        if (alreadySavedInDatabase(sensorLastUpdate)) {
            return;
        }

        log.info("Saving room temperature and humidity to database");
        temperatureRepo.save(
                Temperature.builder()
                        .time(sensorLastUpdate)
                        .temperature(temp.getState())
                        .humidity(hum.getState())
                        .build()
        );

    }

    private boolean alreadySavedInDatabase(LocalDateTime sensorLastUpdate) {
        Temperature lastRowFromTable = temperatureRepo.findTop1ByOrderByIdDesc();

        if (lastRowFromTable == null) {
            return false;
        }
        return sensorLastUpdate.isEqual(lastRowFromTable.getTime().truncatedTo(ChronoUnit.SECONDS));
    }

    private SensorState getSensorState(String temperatureEndpoint) {
        return homeAssistantClient.get().uri(uriBuilder -> uriBuilder
                        .path(temperatureEndpoint)
                        .build()
                ).header("Authorization", "Bearer " + homeAssistantToken)
                .retrieve()
                .bodyToMono(SensorState.class)
                .block();
    }
}
