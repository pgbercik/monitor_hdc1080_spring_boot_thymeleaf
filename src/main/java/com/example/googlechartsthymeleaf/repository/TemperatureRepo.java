package com.example.googlechartsthymeleaf.repository;

import com.example.googlechartsthymeleaf.entity.room_data.Temperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureRepo extends CrudRepository<Temperature, Long> {
    /**
     * last 60 measurements
     */
    List<Temperature> findTop60ByOrderByIdDesc();

    /**
     * find measurements that are
     * not older than certain date and are taken in specified intervals
     *
     * @param localDateTime measurement timestamp will be >= to this date
     * @param minuteDivider minutes extracted from measurement time are divisible by this number
     */
    @Query("SELECT t FROM Temperature t WHERE t.time >= :localDateTime AND mod(MINUTE(t.time), :minuteDivider) = 0 order by t.id")
    List<Temperature> findLastMeasurements(LocalDateTime localDateTime, int minuteDivider);

    Temperature findTop1ByOrderByIdDesc();


}