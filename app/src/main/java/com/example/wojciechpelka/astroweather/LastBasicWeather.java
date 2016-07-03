package com.example.wojciechpelka.astroweather;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-07-03.
 */
public class LastBasicWeather implements Serializable
{
    private String lastLocation;
    private String lastTime;
    private String lastTemperature;
    private String lastAirPressure;
    private String lastDescription;
    private int code;
    private String lastCity;

    public int getLastCode() {
        return code;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public String getLastTime() {
        return lastTime;
    }

    public String getLastTemperature() {
        return lastTemperature;
    }

    public String getLastAirPressure() {
        return lastAirPressure;
    }

    public String getLastDescription() {
        return lastDescription;
    }

    public String getLastCity() {
        return lastCity;
    }

    public LastBasicWeather(String lastLocation, String lastTime, String lastTemperature, String lastAirPressure, String lastDescription, int code, String lastCity) {
        this.lastLocation = lastLocation;
        this.lastTime = lastTime;
        this.lastTemperature = lastTemperature;
        this.lastAirPressure = lastAirPressure;
        this.lastDescription = lastDescription;
        this.code = code;
        this.lastCity = lastCity;
    }
}
