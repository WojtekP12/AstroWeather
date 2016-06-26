package com.example.wojciechpelka.astroweather.data;

import org.json.JSONObject;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Item implements JSONPopulator
{
    private  Condition condition;
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        lat = data.optString("lat");
        lng = data.optString("long");
    }
}
