package com.example.wojciechpelka.astroweather.data;

import org.json.JSONObject;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Atmosphere implements JSONPopulator
{
    private String pressure;
    private String humidity;
    private String visibility;

    public String getHumidity() {
        return humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getPressure() {
        return pressure;
    }

    @Override
    public void populate(JSONObject data)
    {
        pressure = data.optString("pressure");
        humidity = data.optString("humidity");
        visibility = data.optString("visibility");
    }
}
