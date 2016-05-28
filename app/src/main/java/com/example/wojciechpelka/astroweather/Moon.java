package com.example.wojciechpelka.astroweather;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by Wojciech on 2016-05-27.
 */
public class Moon
{
    private static AstroCalculator.MoonInfo moon;

    public static void setMoon(AstroCalculator.MoonInfo _moon)
    {
        moon = _moon;
    }


    public static AstroCalculator.MoonInfo getMoon()
    {
        return moon;
    }

}
