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
        AstroCalculator.SunInfo sunInfo = AstroCalculatorData.GetAstroData().getSunInfo();
        sun = sunInfo;
    }

    public static AstroCalculator.SunInfo getSun()
    {
        return sun;
    }
}
