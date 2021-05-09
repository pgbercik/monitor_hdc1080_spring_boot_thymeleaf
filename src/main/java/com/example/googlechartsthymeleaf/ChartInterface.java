package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ChartInterface {

    /**
     * We're forwarding data from getChartDate() to the chart.*/
    String index(Model model);

    /**
     * We're retrieving data from database using a query. A query result is given to the method as a parameter.
     * */
    default List<List<Object>> getChartData(List<Temperature> list) {

        return formatDataForChart(list);
    }

    /**
     * We're formatting data so tht it is readable by GoogleChart.
     * */
    private List<List<Object>> formatDataForChart(List<Temperature> list) {
        //list with subsequent rows containing time, temperature, humidity in each row
        List<List<Object>> targetList = new ArrayList<>();

        if (!list.isEmpty()) {
            list.forEach(temperature -> {
                String czas = String.valueOf(temperature.getCzas());
                czas = czas.substring(11,16);
                double temp = temperature.getTemperature();
                double hum = temperature.getHumidity();

                //adding another row to the list
                targetList.add(List.of(czas, temp, hum));
                System.out.println(czas+ " | "+ temp+" | "+ hum);

            });
        } else {
            targetList.add(List.of(LocalDateTime.now(),0.0,0.0));
        }
        System.out.println("rows found in "+getClass().getName()+" : "+ (long) targetList.size());

        return targetList;
    }

}
