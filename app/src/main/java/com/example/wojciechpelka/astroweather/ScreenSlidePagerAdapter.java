package com.example.wojciechpelka.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by wojciech.pelka on 2016-05-23.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
{
    private static final int NUM_PAGES = 5;
    public ScreenSlidePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
            {
                return new MoonFragment();
            }
            case 1:
            {
                return new SunFragment();
            }
            case 2:
            {
                return new BasicWeatherFragment();
            }
            case 3:
            {
                return new AdditionalWeatherFragment();
            }
            case 4:
            {
                return new ForecastFragment();
            }
            default:
            {
                return new MoonFragment();
            }
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
