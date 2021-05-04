package com.example.googlechartsthymeleaf.data_model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepo extends CrudRepository<Temperature,Long> {
    //    do tabelki
    public List<Temperature> findTop60ByOrderByIdDesc();
    public List<Temperature> findAll();

    //do wykresu

    /*24h*/
    @Query("SELECT t FROM Temperature t WHERE t.id >=((Select max(t.id) from Temperature t) -1440) AND MOD (t.id,2.5)=0 order by t.id")
    public List<Temperature> findLast24h();

    /*kilka h*/
    @Query("SELECT t FROM Temperature t WHERE t.id >=((Select max(t.id) from Temperature t) -360) order by t.id")
    public List<Temperature> findTop360();


}