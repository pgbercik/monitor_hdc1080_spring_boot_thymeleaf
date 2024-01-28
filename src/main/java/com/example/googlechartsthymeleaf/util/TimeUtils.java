package com.example.googlechartsthymeleaf.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtils {
    private TimeUtils() {}


    /**
     * Converts epoch time to LocalDateTime
     *
     * @param epochTime epoch time in seconds
     * @param offset    time offset in seconds
     * @return LocalDateTime
     */
    public static LocalDateTime epochToLocalDateTime(Long epochTime, Short offset) {
        return LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.ofTotalSeconds(offset));
    }


}
