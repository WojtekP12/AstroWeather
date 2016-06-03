package com.example.wojciechpelka.astroweather;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by wojciech.pelka on 2016-06-03.
 */
public class AstroCalculatorData
{
    public static AstroCalculator GetAstroData()
    {
        AstroDateTime date = new AstroDateTime(CurrentTime.getYear(),CurrentTime.getMonth(),CurrentTime.getDay(),CurrentTime.getHour(),CurrentTime.getHour(),CurrentTime.getSecond(),1,false);
        AstroCalculator.Location location = new AstroCalculator.Location(CurrentLocalization.lat,CurrentLocalization.lng);

        AstroCalculator calculator = new AstroCalculator(date,location);

        return calculator;
    }
}
