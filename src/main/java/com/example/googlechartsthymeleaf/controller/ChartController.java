package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.service.ChartDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SuppressWarnings("unused")
@Controller
public class ChartController {

    private final ChartDataService chartDataService;
    public ChartController(ChartDataService chartDataService) {
        this.chartDataService = chartDataService;
    }
    @GetMapping("/chart")
    public String index(Model model, @RequestParam(defaultValue = "6h") String type) {
        List<List<Object>> data;

        //depending on RequestParam we fetch data for 24h or 6h chart
        if (type.equals("6h")) data = chartDataService.getChartData6H();
        else if (type.equals("24h")) data = chartDataService.getChartData24H();
        else data = chartDataService.getChartData24hOutside();


        if (!data.isEmpty()) {
            model.addAttribute("chartData", data); //adding data fetched from DB
            model.addAttribute("type",type); //adding parameter that defines
            // whether we set description for 6h chart or 24h

            return "chart";
        }
        //if list is empty, we redirect to error page
        else return "chartError";
    }


}
