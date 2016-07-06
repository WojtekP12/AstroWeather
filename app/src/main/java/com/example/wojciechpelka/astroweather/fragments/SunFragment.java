package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;
import com.example.wojciechpelka.astroweather.CurrentTime;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.Sun;
import com.example.wojciechpelka.astroweather.converters.TimeFormatter;

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
    ImageView sunImage;

    int sunRiseHour;
    int sunRiseMinute;
    int sunSetHour;
    int sunSetMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.sun_layout,container,false);
        setFragmentControls(rootView);
        if(ApplicationSettings.getSettings()!=null)
        {
            SetSunInfoEvery(ApplicationSettings.getSettings().getRefresh());
        }
        else
        {
            SetSunInfoEvery(1);
        }
        return rootView;
    }

    private void setFragmentControls(ViewGroup rootView)
    {
        setSunRiseFragmentControls(rootView);
        setSunSetFragmentControls(rootView);
        setOtherSunFragmentControls(rootView);
    }

    private void setOtherSunFragmentControls(ViewGroup rootView)
    {
        dawnValue = (TextView)rootView.findViewById(R.id.sunDawnValue);
        twilightValue = (TextView)rootView.findViewById(R.id.sunTwilightValue);
        sunImage = (ImageView)rootView.findViewById(R.id.sunImage);
    }

    private void setSunSetFragmentControls(ViewGroup rootView)
    {
        sunSetTimeValue = (TextView)rootView.findViewById(R.id.sunsetTimeValue);
        sunSetAzimuthValue = (TextView)rootView.findViewById(R.id.sunsetAzimuthValue);
    }

    private void setSunRiseFragmentControls(ViewGroup rootView)
    {
        sunRiseTimeValue = (TextView)rootView.findViewById(R.id.sunriseTimeValue);
        sunRiseAzimuthValue = (TextView)rootView.findViewById(R.id.sunriseAzimuthValue);
    }

    private void setSunInfo()
    {
        Sun.setSun();
        AstroCalculator.SunInfo sun = Sun.getSun();

        setSunRiseInfo(sun);

        setSunSetinfo(sun);

        setOtherSunInfo(sun);

        setSunImage();
    }

    private void setOtherSunInfo(AstroCalculator.SunInfo sun)
    {
        dawnValue.setText(TimeFormatter.getFormattedTime(sun.getTwilightEvening().getHour(),sun.getTwilightEvening().getMinute(),sun.getTwilightEvening().getSecond()));
        twilightValue.setText(TimeFormatter.getFormattedTime(sun.getTwilightMorning().getHour(),sun.getTwilightMorning().getMinute(),sun.getTwilightMorning().getSecond()));
    }

    private void setSunSetinfo(AstroCalculator.SunInfo sun)
    {
        sunSetTimeValue.setText(TimeFormatter.getFormattedTime(sun.getSunset().getHour(),sun.getSunset().getMinute(),sun.getSunset().getSecond()));
        sunSetHour = sun.getSunset().getHour();
        sunSetMinute = sun.getSunset().getMinute();
        sunSetAzimuthValue.setText(String.valueOf(Math.round(sun.getAzimuthSet()*100.0)/100.0));
    }

    private void setSunRiseInfo(AstroCalculator.SunInfo sun)
    {
        sunRiseTimeValue.setText(TimeFormatter.getFormattedTime(sun.getSunrise().getHour(), sun.getSunrise().getMinute(),sun.getSunrise().getSecond()));
        sunRiseHour = sun.getSunrise().getHour();
        sunRiseMinute = sun.getSunrise().getMinute();
        sunRiseAzimuthValue.setText(String.valueOf(Math.round(sun.getAzimuthRise()*100.0)/100.0));
    }

    private void setSunImage()
    {
        if(sunImage!=null)
        {
            sunImage.setImageResource(getSunImage());
        }
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private int getSunImage()
    {
        int currentHour = CurrentTime.getHour();

        if(currentHour>=12 && currentHour<sunSetHour)
        {
            return R.drawable.sun;
        }
        else if(currentHour>=sunRiseHour && currentHour<12)
        {
            return R.drawable.sunrise;
        }
        else
        {
            return R.drawable.sunset;
        }
    }

}
