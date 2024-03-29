package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.dto.ChartType;
import com.example.googlechartsthymeleaf.service.ChartDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Controller
@Slf4j
public class ChartController {

    private final ChartDataService chartDataService;

    public ChartController(ChartDataService chartDataService) {
        this.chartDataService = chartDataService;
    }

    @GetMapping("/chart")
    public String index(Model model, @RequestParam(defaultValue = "6h") String type) {

        Optional<ChartType> chartType = Arrays.stream(ChartType.values())
                .filter(c -> c.getValue().equals(type))
                .findFirst();

        if (chartType.isEmpty()) {
            model.addAttribute("type", type);
            return "chartDoesNotExist";
        }

        List<List<Object>> data = switch (chartType.get()) {
            case ROOM_6H -> chartDataService.getChartData6H();
            case ROOM_24H -> chartDataService.getChartData24H();
            case OUTSIDE_24H -> chartDataService.getChartData24hOutside();
            case OUTSIDE_5_DAYS_FORECAST -> chartDataService.getChartDataFiveDays();
        };

        model.addAttribute("chartData", data);
        model.addAttribute("type", type);

        return !data.isEmpty() ? "chart" : "chartError";
    }
}
