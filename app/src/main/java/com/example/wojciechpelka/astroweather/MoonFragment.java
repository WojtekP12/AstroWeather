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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.moon_layout,container,false);
        setFragmentControls(rootView);

        final Handler moonHandler = new Handler();

        moonHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                setMoonInfo();
                moonHandler.postDelayed(this,5000);
            }
        },5000);


        return rootView;
    }

    private void setFragmentControls(ViewGroup rootView)
    {
        moonRiseValue = (TextView)rootView.findViewById(R.id.moonriseValue);
        moonSetValue = (TextView)rootView.findViewById(R.id.moonsetValue);
        newMoonValue = (TextView)rootView.findViewById(R.id.newmoonValue);
        fullMoonValue = (TextView)rootView.findViewById(R.id.fullmoonValue);
        phaseValue = (TextView)rootView.findViewById(R.id.moonPhaseValue);
        lunationValue = (TextView)rootView.findViewById(R.id.lunationValue);
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
        lunationValue.setText(String.valueOf((Math.round(moon.getIllumination()*1000000.0))/1000000.0));
    }
}
