package com.woowahan.techcourse.utils;

import java.time.LocalDateTime;

public class DateUtils {

    private DateUtils() {
    }

    /**
     * LocalDateTime.now() 을 포장한 메소드
     *
     * @return the LocalDateTime.now()
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}