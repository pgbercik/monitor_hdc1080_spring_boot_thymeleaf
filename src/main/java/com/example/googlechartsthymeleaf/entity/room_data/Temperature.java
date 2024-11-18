package com.example.googlechartsthymeleaf.entity.room_data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
    @Column(nullable = false, updatable = false)
    private LocalDateTime time;


    public Temperature(Float temperature, Float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;

    }

    public Temperature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timeFormatter.format(time);
    }


    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", czas='" + time + '\'' +
                '}';
    }
}
