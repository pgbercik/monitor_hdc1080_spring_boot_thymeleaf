package com.example.googlechartsthymeleaf.domain;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "my_room")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float temperature;

    @Column(nullable = false)
    private Float humidity;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable=false)
    private Date czas;


    public Temperature(Float temperature, Float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;

    }

    public Temperature() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Float getTemperature() { return temperature; }

    public void setTemperature(Float temperature) { this.temperature = temperature; }

    public Float getHumidity() { return humidity; }

    public void setHumidity(Float humidity) { this.humidity = humidity; }

    public String getCzas() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timeFormatter.format(czas);
    }



    public void setCzas(Date czas) { this.czas = czas; }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", czas='" + czas + '\'' +
                '}';
    }
}
