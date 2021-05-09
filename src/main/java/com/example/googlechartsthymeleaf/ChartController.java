package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class ChartController implements ChartInterface {

    @Autowired
    private TemperatureRepo temperatureRepo;


    @GetMapping
    public String index(Model model) {
        // we retrieve data from database(getChartData))
        //then we forward data to html template and return this template
        model.addAttribute("chartData", getChartData(temperatureRepo.findTop360()));
        return "chart";
    }


}
