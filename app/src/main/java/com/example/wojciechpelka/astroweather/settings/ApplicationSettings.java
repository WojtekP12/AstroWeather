package com.example.wojciechpelka.astroweather.settings;

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
    private static boolean isConnectedToNetwerk;

    public static void setIsConnectedToNetwerk(boolean isConnectedToNetwerk) {
        ApplicationSettings.isConnectedToNetwerk = isConnectedToNetwerk;
    }

    public static boolean getIsConnectedToNetwerk() {
        return isConnectedToNetwerk;
    }

    public static Settings getSettings()
    {
        return settings;
    }

    public static String path;
}
