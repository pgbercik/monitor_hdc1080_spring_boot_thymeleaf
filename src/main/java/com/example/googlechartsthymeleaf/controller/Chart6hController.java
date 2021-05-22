package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class Chart6hController {

    @Autowired
    ChartDataService chartDataService;


    @GetMapping
    public String index(Model model) {
        // we retrieve data from database(getChartData6H))
        //then we forward data to html template and return this template
        var data = chartDataService.getChartData6H();
        if (!data.isEmpty()) {
            model.addAttribute("chartData", data);
            return "chart";
        }
        else return "chartError";
    }


}
