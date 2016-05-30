package com.example.wojciechpelka.astroweather;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by 187783 on 5/30/2016.
 */
public class Sun
{
    private static AstroCalculator.SunInfo sun;

    public static void setSun()
    {
        CurrentTime.year = 2016;
        CurrentTime.month = 5;
        CurrentTime.day = 28;

        AstroDateTime date = new AstroDateTime(CurrentTime.year,CurrentTime.month,CurrentTime.day,CurrentTime.hour,CurrentTime.minute,CurrentTime.second,1,false);
        AstroCalculator.Location location = new AstroCalculator.Location(CurrentLocalization.lat,CurrentLocalization.lng);

        AstroCalculator calculator = new AstroCalculator(date,location);

        AstroCalculator.SunInfo sunInfo = calculator.getSunInfo();

        sun = sunInfo;
    }

    public static AstroCalculator.SunInfo getSun()
    {
        return sun;
    }
}
