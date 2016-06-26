package com.example.wojciechpelka.astroweather.data;

import org.json.JSONObject;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Location implements JSONPopulator {

    private String city;

    public String getCity() {
        return city;
    }

    @Override
    public void populate(JSONObject data) {
        city = data.optString("city");
    }
}
