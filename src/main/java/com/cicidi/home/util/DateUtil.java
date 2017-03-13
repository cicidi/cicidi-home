package com.cicidi.home.util;

import org.springframework.social.linkedin.api.LinkedInDate;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cicidi on 3/11/2017.
 */
public class DateUtil {
    private static String monthNames[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    static Calendar calendar = Calendar.getInstance();

    public static int getYear(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date convert(LinkedInDate linkedInDate) {

        calendar.set(Calendar.YEAR, linkedInDate.getYear());
        calendar.set(Calendar.MONTH, linkedInDate.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, linkedInDate.getDay());
        return new Date(calendar.getTimeInMillis());
    }

    public static String convertToString(LinkedInDate linkedInDate) {
        String value = convertToString(convert(linkedInDate));
        return value;
    }

    public static String convertToString(Date date) {
        calendar.setTime(date);
        String value = monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
        return value;
    }


    public static String calLength(LinkedInDate start, LinkedInDate end) {
        return calLength(convert(start), convert(end));
    }

    public static String calLength(Date start, Date end) {
        String length;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int year_start = calendar.get(Calendar.YEAR);
        int month_start = calendar.get(Calendar.MONTH);
        calendar.setTime(end);
        int year_end = calendar.get(Calendar.YEAR);
        int month_end = calendar.get(Calendar.MONTH);
        int i = (year_end - year_start) * 12 + month_end - month_start;
        int y = i / 12;
        int m = i % 12;
        StringBuilder sb = new StringBuilder();
        if (y > 0) {
            String yrs = "yrs ";
            if (y == 1) {
                yrs = "yr ";
            }
            sb.append(y + yrs);
        }
        if (m > 0) {
            String mos = "mos ";
            if (m == 1) {
                mos = "mo ";
            }
            sb.append(m + mos);
        }
        length = sb.toString().trim();
        return length;
    }
}
