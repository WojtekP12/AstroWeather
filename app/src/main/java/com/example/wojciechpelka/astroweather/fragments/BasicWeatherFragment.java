package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wojciechpelka.astroweather.activities.SettingsActivity;
import com.example.wojciechpelka.astroweather.serialization.Serializer;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;
import com.example.wojciechpelka.astroweather.LastBasicWeather;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.converters.UnitsConverter;
import com.example.wojciechpelka.astroweather.data.Chanel;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.service.WeatherServiceCallback;
import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Wojciech on 2016-06-25.
 */
public class BasicWeatherFragment extends Fragment implements WeatherServiceCallback {

    ImageView weatherImage;
    TextView locationValue;
    TextView tempeartureValue;
    TextView descriptionValue;
    TextView titleValue;
    TextView timeValue;
    TextView airPressureValue;

    private YahooWeatherService service;

    LastBasicWeather lastBasicWeather;
    String lastBasicWeatherPath = ApplicationSettings.path + "lastBasicWeather.bin";

    ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.basic_weather_information_layout,container,false);
        setHasOptionsMenu(true);

        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        locationValue = (TextView)rootView.findViewById(R.id.locationValue);
        tempeartureValue = (TextView)rootView.findViewById(R.id.temperatureValue);
        descriptionValue = (TextView)rootView.findViewById(R.id.descriptionValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        timeValue = (TextView)rootView.findViewById(R.id.timeValue);
        airPressureValue = (TextView)rootView.findViewById(R.id.airPressureValue);

        setUpWeather();

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWeather() {
        if(ApplicationSettings.getIsConnectedToNetwerk())
        {
            service = new YahooWeatherService(this);
            service.refreashWeather(ApplicationSettings.getSettings().getCity()+", "+ApplicationSettings.getSettings().getCountry());
        }
        else
        {
            lastBasicWeather = (LastBasicWeather) Serializer.Deserialize(lastBasicWeatherPath);

            if(lastBasicWeather==null)
            {
                lastBasicWeather = new LastBasicWeather("Loading...","Loading...","Loading...","Loading...","Loading...",44,"Loading...");
            }

            if(isAdded())
            {
                int resource = getResources().getIdentifier("drawable/weather"+lastBasicWeather.getLastCode(),null,getActivity().getPackageName());
                Drawable weatherIconDrawable = getResources().getDrawable(resource,null);
                weatherImage.setImageDrawable(weatherIconDrawable);
            }
            titleValue.setText(lastBasicWeather.getLastCity());
            locationValue.setText(lastBasicWeather.getLastLocation());
            tempeartureValue.setText(lastBasicWeather.getLastTemperature());
            timeValue.setText(lastBasicWeather.getLastTime());
            airPressureValue.setText(lastBasicWeather.getLastAirPressure());
            descriptionValue.setText(lastBasicWeather.getLastDescription());
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void serviceSuccess(Chanel chanel)
    {
        Item item = chanel.getItem();

        if(isAdded())
        {
            int resource = getResources().getIdentifier("drawable/weather"+item.getCondition().getCode(),null,getActivity().getPackageName());
            Drawable weatherIconDrawable = getResources().getDrawable(resource,null);
            weatherImage.setImageDrawable(weatherIconDrawable);
        }

        titleValue.setText(chanel.getLocation().getCity());
        locationValue.setText(item.getLat() + ", " + item.getLng());
        tempeartureValue.setText(UnitsConverter.temperature(String.valueOf(item.getCondition().getTemperature())));
        timeValue.setText(chanel.getTime());
        airPressureValue.setText(chanel.getAtmosphere().getPressure()+" "+chanel.getUnits().getPressure());
        descriptionValue.setText(item.getCondition().getDescription());

        lastBasicWeather = new LastBasicWeather(locationValue.getText().toString(),timeValue.getText().toString(),tempeartureValue.getText().toString(),airPressureValue.getText().toString(),descriptionValue.getText().toString(),item.getCondition().getCode(),titleValue.getText().toString());
        Serializer.Serialize(lastBasicWeather,lastBasicWeatherPath);
    }

    @Override
    public void serviceFailure(Exception ex)
    {
        if(isAdded()) {
            Toast.makeText(getContext(), "0 RESULTS!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                rootView.invalidate();
                ApplicationSettings.setIsConnectedToNetwerk(isNetworkAvailable());

                setUpWeather();

                if(isAdded())
                {
                    Toast.makeText(getContext(), "refreshed", Toast.LENGTH_SHORT).show();
                    if(ApplicationSettings.getIsConnectedToNetwerk())
                    {

                        Toast.makeText(getContext(), "data has been downloaded from YAHOO", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),"No Internet Connection!",Toast.LENGTH_LONG).show();
                    }
                }

                return false;
            default:
                break;
        }

        return false;
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
