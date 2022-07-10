package com.example.googlechartsthymeleaf.controller;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import com.example.googlechartsthymeleaf.service.TableDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ResultTableController {

    private final TableDataService tableDataService;

    public ResultTableController(TableDataService tableDataService) {
        this.tableDataService = tableDataService;
    }

    @GetMapping("/")
    public String get(Model model) {
        List<Temperature> tempHumList = tableDataService.getDataForTable();
        model.addAttribute("tempHumList", tempHumList);

        return "index";
    }
}
