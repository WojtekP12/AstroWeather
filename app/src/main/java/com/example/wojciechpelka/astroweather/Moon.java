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
    public static int phase;

    public static void setMoon()
    {
        AstroCalculator.MoonInfo moonInfo = AstroCalculatorData.GetAstroData().getMoonInfo();
        moon = moonInfo;
    }


    public static AstroCalculator.MoonInfo getMoon()
    {
        return moon;
    }

}
