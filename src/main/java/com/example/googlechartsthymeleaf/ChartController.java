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
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private TemperatureRepo temperatureRepo;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("chartData", getChartData());
        return "chart";
    }

    private List<List<Object>> getChartData() {
        //lista z kolejnymi wierszami danych do wykresu (czas, temperatura, wilgotność)
        List<List<Object>> dataList = new ArrayList<>();

        List<Temperature> list = temperatureRepo.findTop360();
        list.forEach(temperature -> {
            String czas = String.valueOf(temperature.getCzas());
            czas = czas.substring(11,16);
            double temp = temperature.getTemperature();
            double hum = temperature.getHumidity();

            //dodajemy do listy kolejny wiersz
            dataList.add(List.of(czas, temp, hum));

        });
        return dataList;
    }
}
