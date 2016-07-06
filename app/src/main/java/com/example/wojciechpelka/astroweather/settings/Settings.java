package com.example.wojciechpelka.astroweather.settings;

import java.io.Serializable;

/**
 * Created by 187783 on 5/30/2016.
 */
public class Settings implements Serializable
{
    private double lat;
    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }


    private double lng;
    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }


    private int refresh;
    public int getRefresh()
    {
        return refresh;
    }

    public void setRefresh(int refresh)
    {
        this.refresh = refresh;
    }

    private String city;
    private String country;
    private String speedUnit;
    private String degreesUnit;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getSpeedUnit() {
        return speedUnit;
    }

    public String getDegreesUnit() {
        return degreesUnit;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public  void setCountry(String country) {
        this.country = country;
    }

    public  void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    public  void setDegreesUnit(String degreesUnit) {
        this.degreesUnit = degreesUnit;
    }

    public Settings(double lat, double lng, int refresh, String city, String country, String speedUnit, String degreesUnit)
    {
        this.lat = lat;
        this.lng = lng;
        this.refresh = refresh;
        this.city = city;
        this.country = country;
        this.speedUnit = speedUnit;
        this.degreesUnit = degreesUnit;
    }

    public Settings() {}
}
