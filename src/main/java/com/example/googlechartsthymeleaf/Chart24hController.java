package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import com.example.googlechartsthymeleaf.data_model.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart24h")
public class Chart24hController {

    @Autowired
    private TemperatureRepo temperatureRepo;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("chartData24h", getChart24hData());
        return "chart24h";
    }

    private List<List<Object>> getChart24hData() {

        //lista z kolejnymi wierszami danych do wykresu (czas, temperatura, wilgotność)
        List<List<Object>> dataList = new ArrayList<>();

        List<Temperature> list = temperatureRepo.findLast24h();
        list.forEach(temperature -> {
            String czas = String.valueOf(temperature.getCzas());
            czas = czas.substring(11,16);
            double temp = temperature.getTemperature();
            double hum = temperature.getHumidity();

            //dodajemy do listy kolejny wiersz
            dataList.add(List.of(czas, temp, hum));
            System.out.println(czas+ " | "+ temp+" | "+ hum);

        });
        System.out.println("wiersze znalezione w chart24h"+ (long) dataList.size());


        return dataList;
    }
}