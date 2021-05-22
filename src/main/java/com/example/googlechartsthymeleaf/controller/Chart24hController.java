package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.service.ChartDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart24h")
public class Chart24hController {

    private  final ChartDataService chartDataService;

    public Chart24hController(ChartDataService chartDataService) {
        this.chartDataService = chartDataService;
    }

    @GetMapping
    public String index(Model model) {
        // we retrieve data from database(getChartData24H))
        //then we forward data to html template and return this template

        var data = chartDataService.getChartData24H();

        if (!data.isEmpty()) {
            model.addAttribute("chartData", data);
            return "chart24h";
        }
        else return "chartError";


    }
}