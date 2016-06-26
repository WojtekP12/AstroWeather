package com.example.wojciechpelka.astroweather.data;

import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

import org.json.JSONObject;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Units implements JSONPopulator {

    private String temperature;
    private String pressure;
    private String speed;

    public String getSpeed() {
        return speed;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    @Override
    public void populate(JSONObject data)
    {
        temperature = data.optString("temperature");
        pressure = data.optString("pressure");
        speed = data.optString("speed");
    }
}
