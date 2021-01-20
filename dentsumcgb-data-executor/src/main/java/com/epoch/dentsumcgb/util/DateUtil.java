package com.epoch.dentsumcgb.util;

import com.epoch.dentsumcgb.config.smb.Smb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fuzhi
 * 时间处理
 */
public class DateUtil {

    private static final String DATA_PATTERN = "yyyy-MM-dd";

    private static final String DATA_PATTERN_HMS = "yyyyMMddHHmmss";

    public static String getFormatDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATA_PATTERN));
    }

    public static String getFormatDateHMS() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_PATTERN_HMS));
    }

    public static void main(String[] args) {
        String name="HYP_HR_DATA.txt";
        System.out.println(name.indexOf("."));
        String[] d=name.split("\\.");

        System.out.println(d[0]);
    }
}
