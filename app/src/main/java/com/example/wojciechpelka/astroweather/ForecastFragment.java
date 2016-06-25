package com.example.wojciechpelka.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wojciech on 2016-06-25.
 */

public class ForecastFragment  extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.foreacast_layout,container,false);

        return rootView;
    }
}