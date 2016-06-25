package com.example.wojciechpelka.astroweather;

import java.util.Calendar;

/**
 * Created by 187783 on 5/30/2016.
 */
public class CurrentTime
{
    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int minute;
    private static int second;

    public static int getYear() {
        return year;
    }

    public static void setYear(int year) {
        CurrentTime.year = year;
    }

    public static int getMonth() {
        return month;
    }

    public static String getFormattedMonth()
    {
        return TimeFormatter.format(month);
    }

    public static void setMonth(int month) {
        CurrentTime.month = month;
    }

    public static int getDay() {
        return day;
    }

    public static String getFormattedDay()
    {
        return TimeFormatter.format(day);
    }

    public static void setDay(int day) {
        CurrentTime.day = day;
    }

    public static int getHour() {
        return hour;
    }

    public static void setHour(int hour) {
        CurrentTime.hour = hour;
    }

    public static int getMinute() {
        return minute;
    }

    public static String getFormattedMinute()
    {
        return TimeFormatter.format(minute);
    }

    public static void setMinute(int minute) {
        CurrentTime.minute = minute;
    }

    public static int getSecond() {
        return second;
    }
    public static String getFormattedSecond()
    {
         return TimeFormatter.format(second);
    }

    public static void setSecond(int second) {
        CurrentTime.second = second;
    }

    public static void SetCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
