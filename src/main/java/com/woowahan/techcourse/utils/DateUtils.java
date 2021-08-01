package com.woowahan.techcourse.utils;

import java.time.LocalDateTime;

public class DateUtils {

    private DateUtils() {
    }

    /**
     * {@link LocalDateTime#now()} 결과를 반환하는 메소드.
     * <p>
     * 현재 컴퓨터의 날짜 및 시간을 반환한다.
     * <p>
     * Example : 2021-08-01T11:41:48.134449
     *
     * @return {@link LocalDateTime#now()}
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
