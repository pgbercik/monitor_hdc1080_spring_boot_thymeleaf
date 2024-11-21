package com.example.googlechartsthymeleaf.repository;

import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepo extends CrudRepository<Temperature,Long> {
    //    do tabelki
    /** last 60 measurements*/
    List<Temperature> findTop60ByOrderByIdDesc();

    //do wykresu

    /**last 24h, measurements taken every 5 minutes*/
    @Query("SELECT t FROM Temperature t WHERE t.id >=((Select max(t.id) from Temperature t) -1440) AND MOD (t.id,5)=0 order by t.id")
    List<Temperature> findLast24h();

    /** last 6h, measurements taken every 1 minute*/
    @Query("SELECT t FROM Temperature t WHERE t.id >=((Select max(t.id) from Temperature t) -360) order by t.id")
    List<Temperature> findTop360();

    Temperature findTop1ByOrderByIdDesc();


}