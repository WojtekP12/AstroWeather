package com.example.wojciechpelka.astroweather;

/**
 * Created by 187783 on 5/30/2016.
 */
public class Settings
{

    private static double lat;
    public static double getLat()
    {
        return lat;
    }

    public static void setLat(double lat)
    {
        Settings.lat = lat;
    }


    private static double lng;
    public static double getLng()
    {
        return lng;
    }

    public static void setLng(double lng)
    {
        Settings.lng = lng;
    }


    private static int refresh;
    public static int getRefresh()
    {
        return refresh;
    }

    public static void setRefresh(int refresh)
    {
        Settings.refresh = refresh;
    }




}
