package com.example.wojciechpelka.astroweather.data;

import com.astrocalculator.AstroCalculator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Channel implements JSONPopulator, Serializable
{
    private Units units;
    private Item item;
    private String time;
    private Location location;
    private Atmosphere atmosphere;
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public Location getLocation() {
        return location;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public String getTime()
    {
        return time;
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject data) throws JSONException {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        location = new Location();
        location.populate(data.optJSONObject("location"));

        atmosphere = new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

        time = data.optString("lastBuildDate");
    }
}
