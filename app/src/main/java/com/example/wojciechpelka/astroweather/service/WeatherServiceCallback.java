package com.example.wojciechpelka.astroweather.service;

import com.example.wojciechpelka.astroweather.data.Chanel;

/**
 * Created by Wojciech on 2016-06-26.
 */
public interface WeatherServiceCallback
{
    void serviceSuccess(Chanel chanel);
    void serviceFailure(Exception ex);
}
