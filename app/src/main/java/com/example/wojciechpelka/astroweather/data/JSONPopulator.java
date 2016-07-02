package com.example.wojciechpelka.astroweather.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wojciech on 2016-06-26.
 */
public interface JSONPopulator
{
    void populate(JSONObject data) throws JSONException;
}
