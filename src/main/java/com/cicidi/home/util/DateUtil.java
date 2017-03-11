package com.cicidi.home.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cicidi on 3/11/2017.
 */
public class DateUtil {
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
}
