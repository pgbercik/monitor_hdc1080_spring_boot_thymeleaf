package com.example.googlechartsthymeleaf;

import com.example.googlechartsthymeleaf.data_model.Temperature;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ChartInterface {

    /**
     * Przekazujemy dane z metody getChartDate() do wykresu.*/
    String index(Model model);

    /**
     * Pobieramy sobie dane z BD za pomocą odpowiedniej kwerendy.
     * */
    default List<List<Object>> getChartData(List<Temperature> list) {

        return formatDataForChart(list);
    }

    /**
     * Formatujemy dane do postaci wymaganej przez Google Chart.
     * */
    private List<List<Object>> formatDataForChart(List<Temperature> list) {
        //lista z kolejnymi wierszami danych do wykresu (czas, temperatura, wilgotność)
        List<List<Object>> targetList = new ArrayList<>();

        if (!list.isEmpty()) {
            list.forEach(temperature -> {
                String czas = String.valueOf(temperature.getCzas());
                czas = czas.substring(11,16);
                double temp = temperature.getTemperature();
                double hum = temperature.getHumidity();

                //dodajemy do listy kolejny wiersz
                targetList.add(List.of(czas, temp, hum));
                System.out.println(czas+ " | "+ temp+" | "+ hum);

            });
        } else {
            targetList.add(List.of(LocalDateTime.now(),0.0,0.0));
        }
        System.out.println("wiersze znalezione w "+getClass().getName()+" : "+ (long) targetList.size());

        return targetList;
    }

}
