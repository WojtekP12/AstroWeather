package com.example.wojciechpelka.astroweather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

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
        rootView = (ViewGroup)inflater.inflate(R.layout.moon_layout,container,false);
        setFragmentControls(rootView);

        setMoonDataEvery(Settings.getRefresh());
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
        moonRiseValue = (TextView)rootView.findViewById(R.id.moonriseValue);
        moonSetValue = (TextView)rootView.findViewById(R.id.moonsetValue);
        newMoonValue = (TextView)rootView.findViewById(R.id.newmoonValue);
        fullMoonValue = (TextView)rootView.findViewById(R.id.fullmoonValue);
        phaseValue = (TextView)rootView.findViewById(R.id.moonPhaseValue);
        lunationValue = (TextView)rootView.findViewById(R.id.lunationValue);
        moonImage = (ImageView)rootView.findViewById(R.id.moonImage);
    }

    public void setMoonInfo()
    {
        Moon.setMoon();
        AstroCalculator.MoonInfo moon = Moon.getMoon();

        moonRiseValue.setText(moon.getMoonrise().getHour() + ":" + moon.getMoonrise().getMinute() + ":" + moon.getMoonrise().getSecond());
        //moonRiseValue.setText(CurrentTime.year + ":" + CurrentTime.month + ":" + CurrentTime.day);
        moonSetValue.setText(moon.getMoonset().getHour() + ":" + moon.getMoonset().getMinute() + ":" + moon.getMoonset().getSecond());
        newMoonValue.setText(moon.getNextNewMoon().getDay() + " - " + moon.getNextNewMoon().getMonth() + " - " + moon.getNextNewMoon().getYear());
        fullMoonValue.setText(moon.getNextFullMoon().getDay() + " - " + moon.getNextFullMoon().getMonth() + " - " +moon.getNextFullMoon().getYear());
        phaseValue.setText(String.valueOf(((Math.round(moon.getAge()*100.0))/100.0)+"%"));
        Moon.phase = (int)(Math.round(moon.getAge()*100.0)/100.0);
        lunationValue.setText(String.valueOf((Math.round(moon.getIllumination()*1000000.0))/1000000.0));
        setMoonPhaseImage(0);
    }

    private void setMoonPhaseImage(int phase)
    {
        if(moonImage!=null)
        {
            if(phase==0)
            {
                moonImage.setImageResource(R.mipmap.moon_0);
            }
            if(phase>0 && phase<45)
            {
                moonImage.setImageResource(R.mipmap.moon_0_45);
            }
            if(phase>=45 && phase<55)
            {
                moonImage.setImageResource(R.mipmap.moon_45_55);
            }
            if(phase>=55 && phase<100)
            {
                moonImage.setImageResource(R.mipmap.moon_55_80);
            }
            if(phase==100)
            {
                moonImage.setImageResource(R.mipmap.moon_100);
            }
        }

    }
}
