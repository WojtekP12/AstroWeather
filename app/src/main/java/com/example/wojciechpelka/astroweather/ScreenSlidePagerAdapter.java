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
    private static final int NUM_PAGES = 2;
    public ScreenSlidePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position==0)
        {
            return new MoonFragment();
        }
        else
        {
            return new SunFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
