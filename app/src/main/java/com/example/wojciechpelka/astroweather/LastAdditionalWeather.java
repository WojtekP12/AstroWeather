package com.example.wojciechpelka.astroweather;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-07-03.
 */
public class LastAdditionalWeather implements Serializable
{
    private String lastCity;
    private int code;
    private String lastWindDirection;
    private String lastWindSpeed;
    private String lastHumidity;
    private String lastVisibility;

    public LastAdditionalWeather(String lastCity, int code, String lastWindDirection, String lastWindSpeed, String lastHumidity, String lastVisibility) {
        this.lastCity = lastCity;
        this.code = code;
        this.lastWindDirection = lastWindDirection;
        this.lastWindSpeed = lastWindSpeed;
        this.lastHumidity = lastHumidity;
        this.lastVisibility = lastVisibility;
    }

    public String getLastCity() {

        return lastCity;
    }

    public int getLastCode() {
        return code;
    }

    public String getLastWindDirection() {
        return lastWindDirection;
    }

    public String getLastWindSpeed() {
        return lastWindSpeed;
    }

    public String getLastHumidity() {
        return lastHumidity;
    }

    public String getLastVisibility() {
        return lastVisibility;
    }
}
