package com.example.wojciechpelka.astroweather.service;

import com.example.wojciechpelka.astroweather.data.Channel;

/**
 * Created by Wojciech on 2016-06-26.
 */
public interface WeatherServiceCallback
{
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception ex);
}
