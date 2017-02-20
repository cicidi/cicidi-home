package com.cicidi.home.domain.vo;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by cicidi on 2/19/2017.
 */
public class WebLog {
    private Calendar calendar;
    private String title;
    private String message;
    private String link;

    private int day;
    private String month;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message.contains("-")) {
            int index = message.indexOf("-");
            this.title = message.substring(0, index);
            this.message = message.substring(index + 1);
        } else {
            this.message = message;
        }

    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getDay() {
        return day;
    }


    public String getMonth() {
        return month;
    }


    public int getYear() {
        return year;
    }


    public void setCalendar(Calendar calendar) {
        this.year = calendar.get(Calendar.YEAR);
        int i = calendar.get(Calendar.MONTH);
        this.month = new DateFormatSymbols().getMonths()[i - 1];
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.calendar = calendar;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String sha) {
        this.link = "https://github.com/cicidi/cicidi-home/commit/" + sha;
    }
}
