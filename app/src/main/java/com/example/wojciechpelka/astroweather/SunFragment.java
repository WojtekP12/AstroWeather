package com.example.wojciechpelka.astroweather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
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
public class SunFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.sun_layout,container,false);

        final Handler sunHandler = new Handler();

        sunHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                setSunInfo(rootView);
                sunHandler.postDelayed(this,5000);
            }
        },5000);

        return rootView;
    }

    public void setSunInfo(ViewGroup rootView)
    {
        Sun.setSun();
        AstroCalculator.SunInfo sun = Sun.getSun();
        AstroDateTime sunRiseTime = sun.getSunrise();
        double sunRiseAzimuth = sun.getAzimuthRise();
        AstroDateTime sunSet = sun.getSunset();
        double sunSetAzimuth = sun.getAzimuthSet();
        AstroDateTime dawn = sun.getTwilightEvening();
        AstroDateTime twilight = sun.getTwilightMorning();

        TextView sunRiseTimeValue = (TextView)rootView.findViewById(R.id.sunriseTimeValue);
        sunRiseTimeValue.setText(sunRiseTime.getDay() + " - " + sunRiseTime.getMonth() + " - " + sunRiseTime.getYear() + " " + sunRiseTime.getHour() +"/"+sunRiseTime.getMinute()+"/"+sunRiseTime.getSecond());

        TextView sunRiseAzimuthValue = (TextView)rootView.findViewById(R.id.sunriseAzimuthValue);
        sunRiseAzimuthValue.setText(String.valueOf(sunRiseAzimuth));

        TextView sunSetTimeValue = (TextView)rootView.findViewById(R.id.sunsetTimeValue);
        sunSetTimeValue.setText(sunSet.getDay() + " - " + sunSet.getMonth() + " - " + sunSet.getYear() + " " + sunSet.getHour() +"/"+sunSet.getMinute()+"/" +sunSet.getSecond());

        TextView sunSetAzimuthValue = (TextView)rootView.findViewById(R.id.sunsetAzimuthValue);
        sunSetAzimuthValue.setText(String.valueOf(sunSetAzimuth));

        TextView dawnValue = (TextView)rootView.findViewById(R.id.sunDawnValue);
        dawnValue.setText(dawn.getDay() + " - " + dawn.getMonth() + " - " + dawn.getYear() + " " + dawn.getHour() +"/"+dawn.getMinute()+"/" +dawn.getSecond());

        TextView twilightValue = (TextView)rootView.findViewById(R.id.sunTwilightValue);
        twilightValue.setText(twilight.getDay() + " - " + twilight.getMonth() + " - " + twilight.getYear() + " " + twilight.getHour() +"/"+twilight.getMinute()+"/" +twilight.getSecond());
    }
}
