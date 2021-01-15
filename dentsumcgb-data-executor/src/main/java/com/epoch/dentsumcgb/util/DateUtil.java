package com.epoch.dentsumcgb.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author fuzhi
 * 时间处理
 */
public class DateUtil {

    private static final String DATA_PATTERN = "yyyy-MM-dd";

    public static String getFormatDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATA_PATTERN));

    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getFormatDate());
    }
}
