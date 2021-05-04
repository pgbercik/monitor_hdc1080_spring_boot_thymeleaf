package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import com.example.googlechartsthymeleaf.data_model.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainPageController {

    @Autowired
    private TemperatureRepo temperatureRepo;

    @GetMapping("/")
    public String get(Model model) {
        List<Temperature> tempHumList = temperatureRepo.findTop60ByOrderByIdDesc();
        tempHumList.forEach(System.out::println);
        model.addAttribute("tempHumList",tempHumList);
        return "index";
    }
}
