package com.green.hoteldog.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

//랜덤코드 생성
public class RandomCodeUtils {
    public static String getRandomCode(int length) {
        LocalDateTime date = LocalDateTime.now();
        String code = "" + date.getYear() + date.getMonthValue() + date.getDayOfMonth();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 10);
            code += random;
        }
        code += date.getHour() + date.getMinute() + date.getSecond();
        return code;
    }

}
