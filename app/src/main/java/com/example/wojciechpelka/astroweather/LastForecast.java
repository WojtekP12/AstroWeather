package com.example.wojciechpelka.astroweather;

import com.example.wojciechpelka.astroweather.data.ForecastDay;
import com.example.wojciechpelka.astroweather.data.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-07-03.
 */
public class LastForecast implements Serializable
{
    private List<ForecastDay> lastForecastDays = new ArrayList<>();
    private String lastCity;

    public String getLastCity() {

        return lastCity;
    }

    public List<ForecastDay> getLastForecastDays() {
        return lastForecastDays;
    }

    public LastForecast(List<ForecastDay> lastForecastDays, String lastCity) {
        this.lastForecastDays = lastForecastDays;
        this.lastCity = lastCity;
    }
}
