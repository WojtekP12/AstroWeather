package com.example.wojciechpelka.astroweather;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wojciechpelka.astroweather.fragments.AdditionalWeatherFragment;
import com.example.wojciechpelka.astroweather.fragments.BasicWeatherFragment;
import com.example.wojciechpelka.astroweather.fragments.ForecastFragment;
import com.example.wojciechpelka.astroweather.fragments.MoonFragment;
import com.example.wojciechpelka.astroweather.fragments.SunFragment;

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
    public float getPageWidth(int position) {
        float nbPages;
        if (DeviceSettings.config.orientation == Configuration.ORIENTATION_LANDSCAPE || DeviceSettings.config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
            nbPages = 2;
        } else {
            nbPages = 1;
        }
        return (1 / nbPages);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
