package com.example.wojciechpelka.astroweather.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.wojciechpelka.astroweather.data.Chanel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class YahooWeatherService
{
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback)
    {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreashWeather(final String location)
    {
        this.location = location;
        new AsyncTask<String, Void, String>()
        {
            @Override
            protected String doInBackground(String... params)
            {
                params[0] = params[0].replace(" ","%20");
                String YQL = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%22"+params[0]+"%22)";

                //String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json",YQL);

                String endpoint = "https://query.yahooapis.com/v1/public/yql?q="+YQL+"&format=json";


                try
                {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!=null)
                    {
                        result.append(line);
                    }

                    return result.toString();

                }
                catch (Exception e)
                {
                    error = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s)
            {
                if(s==null && error !=null)
                {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");
                    if(count == 0)
                    {
                        callback.serviceFailure(new LocationWeatherException("no weather information for "+location));
                        return;
                    }

                    Chanel channel = new Chanel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception
    {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }

}
