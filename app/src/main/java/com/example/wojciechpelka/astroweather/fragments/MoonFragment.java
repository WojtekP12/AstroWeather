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
import com.example.wojciechpelka.astroweather.*;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.converters.TimeFormatter;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;

/**
 * Created by wojciech.pelka on 2016-05-23.
 */
public class MoonFragment extends Fragment
{

    TextView moonRiseValue;
    TextView moonSetValue;
    TextView newMoonValue;
    TextView fullMoonValue;
    TextView phaseValue;
    TextView lunationValue;
    ImageView moonImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(com.example.wojciechpelka.astroweather.R.layout.moon_layout,container,false);
        setFragmentControls(rootView);
        if(ApplicationSettings.getSettings()!=null)
        {
            setMoonDataEvery(ApplicationSettings.getSettings().getRefresh());
        }
        else
        {
            setMoonDataEvery(1);
        }

        return rootView;
    }

    private void setMoonDataEvery(int minutes)
    {
        final int _m = minutes;
        final Handler moonHandler = new Handler();

        moonHandler.postDelayed(new Runnable()
        {
            int time = _m*1000;
            @Override
            public void run()
            {
                setMoonInfo();
                moonHandler.postDelayed(this,time);
            }
        },_m*1000);
    }

    private void setFragmentControls(ViewGroup rootView)
    {
        setMoonRiseMoonSetFragmentControls(rootView);
        setMoonPhaseFragmentControls(rootView);
    }

    private void setMoonPhaseFragmentControls(ViewGroup rootView)
    {
        newMoonValue = (TextView)rootView.findViewById(R.id.newmoonValue);
        fullMoonValue = (TextView)rootView.findViewById(R.id.fullmoonValue);
        phaseValue = (TextView)rootView.findViewById(R.id.moonPhaseValue);
        lunationValue = (TextView)rootView.findViewById(R.id.lunationValue);
        moonImage = (ImageView)rootView.findViewById(R.id.moonImage);
    }

    private void setMoonRiseMoonSetFragmentControls(ViewGroup rootView)
    {
        moonRiseValue = (TextView)rootView.findViewById(R.id.moonriseValue);
        moonSetValue = (TextView)rootView.findViewById(R.id.moonsetValue);
    }

    public void setMoonInfo()
    {
        Moon.setMoon();
        AstroCalculator.MoonInfo moon = Moon.getMoon();
        setMoonRiseMoonSetInfo(moon);
        setMoonPhaseInfo(moon);
    }

    private void setMoonPhaseInfo(AstroCalculator.MoonInfo moon) {
        newMoonValue.setText(TimeFormatter.getFormattedDate(moon.getNextNewMoon().getDay(),moon.getNextNewMoon().getMonth(),moon.getNextNewMoon().getYear()));
        fullMoonValue.setText(TimeFormatter.getFormattedDate(moon.getNextFullMoon().getDay(),moon.getNextFullMoon().getMonth(),moon.getNextFullMoon().getYear()));
        phaseValue.setText(String.valueOf(((Math.round(moon.getAge()*1.0))/1.0)));
        lunationValue.setText(String.valueOf((100.0*Math.round(moon.getIllumination()*10000.0))/10000.0)+" %");
        Moon.phase = (int)((100.0*Math.round(moon.getIllumination()*10000.0))/10000.0);
        setMoonImage();
    }

    private void setMoonRiseMoonSetInfo(AstroCalculator.MoonInfo moon) {
        moonRiseValue.setText(TimeFormatter.getFormattedTime(moon.getMoonrise().getHour(),moon.getMoonrise().getMinute(),moon.getMoonrise().getSecond()));
        moonSetValue.setText(TimeFormatter.getFormattedTime(moon.getMoonset().getHour(),moon.getMoonset().getMinute(),moon.getMoonset().getSecond()));
    }

    private void setMoonImage()
    {
        if(moonImage!=null)
        {
            moonImage.setImageResource(getMoonImage(Moon.phase));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public int getMoonImage(int phase)
    {
        if(phase == 0)
        {
            return R.drawable.moon_0;
        }
        else if(phase > 0 && phase <45)
        {
            return R.drawable.moon_0_45;
        }
        else if(phase >= 45 && phase <55)
        {
            return R.drawable.moon_45_55;
        }
        else if(phase>=55 && phase <80)
        {
            return R.drawable.moon_55_80;
        }
        else
        {
            return R.drawable.moon_100;
        }
    }


}
