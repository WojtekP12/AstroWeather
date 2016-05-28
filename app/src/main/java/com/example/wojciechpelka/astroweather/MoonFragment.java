package com.example.wojciechpelka.astroweather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by wojciech.pelka on 2016-05-23.
 */
public class MoonFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.moon_layout,container,false);
        setMoonInfo(rootView);

        return rootView;
    }

    public void setMoonInfo(ViewGroup rootView)
    {
        AstroCalculator.MoonInfo moon = Moon.getMoon();
        AstroDateTime moonRise = moon.getMoonrise();
        AstroDateTime moonSet = moon.getMoonset();
        AstroDateTime newMoon = moon.getNextNewMoon();
        AstroDateTime fullMoon = moon.getNextFullMoon();
        double phase = moon.getAge();
        double lunation = moon.getIllumination();

        TextView moonRiseValue = (TextView)rootView.findViewById(R.id.moonriseValue);
        moonRiseValue.setText(moonRise.getDay() + " - " + moonRise.getMonth() + " - " + moonRise.getYear());

        TextView moonSetValue = (TextView)rootView.findViewById(R.id.moonsetValue);
        moonSetValue.setText(moonSet.getDay() + " - " + moonSet.getMonth() + " - " + moonSet.getYear());

        TextView newMoonValue = (TextView)rootView.findViewById(R.id.newmoonValue);
        newMoonValue.setText(newMoon.getDay() + " - " + newMoon.getMonth() + " - " +newMoon.getYear());

        TextView fullMoonValue = (TextView)rootView.findViewById(R.id.fullmoonValue);
        fullMoonValue.setText(fullMoon.getDay() + " - " + fullMoon.getMonth() + " - " +fullMoon.getYear());

        TextView phaseValue = (TextView)rootView.findViewById(R.id.moonPhaseValue);
        phaseValue.setText(String.valueOf(Math.round(phase*10.0)/10.0));

        TextView lunationValue = (TextView)rootView.findViewById(R.id.lunationValue);
        lunationValue.setText(String.valueOf(Math.round(lunation*10.0)/10.0));
    }
}
