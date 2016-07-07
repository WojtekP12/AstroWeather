package com.example.wojciechpelka.astroweather.data;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Location implements JSONPopulator, Serializable {

    private String city;

    public String getCity() {
        return city;
    }

    @Override
    public void populate(JSONObject data) {
        city = data.optString("city");
    }
}
