package com.example.wojciechpelka.astroweather;

import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by Wojciech on 2016-05-27.
 */
public class Moon
{
    private static AstroCalculator.MoonInfo moon;

    public static void setMoon()
    {
        CurrentTime.year = 2016;
        CurrentTime.month = 5;
        CurrentTime.day = 28;

        AstroDateTime date = new AstroDateTime(CurrentTime.year,CurrentTime.month,CurrentTime.day,CurrentTime.hour,CurrentTime.minute,CurrentTime.second,1,false);
        AstroCalculator.Location location = new AstroCalculator.Location(CurrentLocalization.lat,CurrentLocalization.lng);

        AstroCalculator calculator = new AstroCalculator(date,location);

        AstroCalculator.MoonInfo moonInfo = calculator.getMoonInfo();

        moon = moonInfo;
    }


    public static AstroCalculator.MoonInfo getMoon()
    {
        return moon;
    }

}