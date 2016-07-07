package com.example.wojciechpelka.astroweather.data;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Wind implements JSONPopulator, Serializable
{
    private String windSpeed;
    private String windDirecetion;

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirecetion() {
        return windDirecetion;
    }

    @Override
    public void populate(JSONObject data) {
        windSpeed = data.optString("speed");
        windDirecetion = data.optString("direction");
    }
}
