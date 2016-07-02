package com.example.wojciechpelka.astroweather;

/**
 * Created by Wojciech on 2016-07-02.
 */
public class ApplicationSettings
{
    private static Settings settings = new Settings(0.0,0.0,1,"Lodz","PL","mph","F");

    public static void setSettings(Settings settings)
    {
        ApplicationSettings.settings = settings;
    }

    public static Settings getSettings()
    {

        return settings;
    }
}
