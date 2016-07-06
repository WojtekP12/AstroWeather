package com.example.wojciechpelka.astroweather.converters;

/**
 * Created by Wojciech on 2016-06-25.
 */
public class TimeFormatter
{
    public static String format(int element)
    {
        if(element<10)
        {
            return "0"+element;
        }
        else
        {
            return String.valueOf(element);
        }
    }

    public static String getFormattedTime(int value1, int value2, int value3)
    {
        return format(value1) + ":" + format(value2) + ":" + format(value3);
    }

    public static String getFormattedDate(int value1, int value2, int value3)
    {
        return format(value1) + " - " + format(value2) + " - " + format(value3);
    }
}
