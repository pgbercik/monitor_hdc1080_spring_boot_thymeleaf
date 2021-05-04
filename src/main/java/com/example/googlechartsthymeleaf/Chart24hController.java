package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart24h")
public class Chart24hController implements ChartInterface {

    @Autowired
    private TemperatureRepo temperatureRepo;


    @GetMapping
    public String index(Model model) {
        //pobieramy sobie dane do wykresu,
        // przekazujemy je do formatki html i tę formatkę zwracamy
        model.addAttribute("chartData24h", getChartData(temperatureRepo.findLast24h()));
        return "chart24h";
    }
}