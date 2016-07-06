package com.example.wojciechpelka.astroweather.converters;

import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;

/**
 * Created by Wojciech on 2016-07-02.
 */
public class UnitsConverter
{
    public static String temperature(String value)
    {
        if(ApplicationSettings.getSettings()!=null)
        {
            if(ApplicationSettings.getSettings().getDegreesUnit().contains("F"))
            {
                return value + " F";
            }
            else
            {
                int temp = ((Integer.parseInt(value)-32)*5)/9;
                return String.valueOf(temp)+" C";
            }
        }
        else
        {
            return value;
        }
    }

    public static String speed(String value)
    {
        if(ApplicationSettings.getSettings()!=null)
        {
            if(ApplicationSettings.getSettings().getSpeedUnit().contains("mph"))
            {
                return value + " mph";
            }
            else
            {
                int speed = (int)(Integer.valueOf(value) * 1.61);
                return String.valueOf(speed) + " km/h";
            }
        }
        else
        {
            return value;
        }

    }

}
