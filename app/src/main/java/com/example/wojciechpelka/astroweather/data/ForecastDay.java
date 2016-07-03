package com.example.wojciechpelka.astroweather.data;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-07-03.
 */
public class ForecastDay implements Serializable
{
    private int code;
    private String date;
    private String day;
    private String high;
    private String low;
    private String text;

    public ForecastDay(int code, String date, String day, String high, String low, String text) {
        this.code = code;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }
}
