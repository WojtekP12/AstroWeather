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
    TextView sunRiseTimeValue;
    TextView sunRiseAzimuthValue;
    TextView sunSetTimeValue;
    TextView sunSetAzimuthValue;
    TextView dawnValue;
    TextView twilightValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.sun_layout,container,false);

        setFragmentControls(rootView);
        SetSunInfoEvery(1);

        return rootView;
    }

    private void setFragmentControls(ViewGroup rootView)
    {
        sunRiseTimeValue = (TextView)rootView.findViewById(R.id.sunriseTimeValue);
        sunRiseAzimuthValue = (TextView)rootView.findViewById(R.id.sunriseAzimuthValue);
        sunSetTimeValue = (TextView)rootView.findViewById(R.id.sunsetTimeValue);
        sunSetAzimuthValue = (TextView)rootView.findViewById(R.id.sunsetAzimuthValue);
        dawnValue = (TextView)rootView.findViewById(R.id.sunDawnValue);
        twilightValue = (TextView)rootView.findViewById(R.id.sunTwilightValue);
    }


    private void setSunInfo()
    {
        Sun.setSun();
        AstroCalculator.SunInfo sun = Sun.getSun();

        sunRiseTimeValue.setText(sun.getSunrise().getHour() +":"+sun.getSunrise().getMinute()+":"+sun.getSunrise().getSecond());
        sunRiseAzimuthValue.setText(String.valueOf(sun.getAzimuthRise()));
        sunSetTimeValue.setText(sun.getSunset().getHour() +":"+sun.getSunset().getMinute()+":" +sun.getSunset().getSecond());
        sunSetAzimuthValue.setText(String.valueOf(sun.getAzimuthSet()));
        dawnValue.setText(sun.getTwilightEvening().getHour() +":"+sun.getTwilightEvening().getMinute()+":" +sun.getTwilightEvening().getSecond());
        twilightValue.setText(sun.getTwilightMorning().getHour() +":"+sun.getTwilightMorning().getMinute()+":" +sun.getTwilightMorning().getSecond());
    }

    private void SetSunInfoEvery(int minutes)
    {
        final int _m = minutes;
        final Handler sunHandler = new Handler();

        sunHandler.postDelayed(new Runnable()
        {
            int time = _m*1000;
            @Override
            public void run()
            {
                setSunInfo();
                sunHandler.postDelayed(this,time);
            }
        },_m*1000);
    }
}
