package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wojciechpelka.astroweather.serialization.Serializer;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;
import com.example.wojciechpelka.astroweather.LastAdditionalWeather;
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
public class AdditionalWeatherFragment extends Fragment implements WeatherServiceCallback
{
    ImageView weatherImage;
    TextView visibilityValue;
    TextView humidityValue;
    TextView titleValue;
    TextView windSpeedValue;
    TextView windDirectionValue;

    String temp;

    private YahooWeatherService service;
    private ProgressDialog dialog;


    LastAdditionalWeather lastAdditionalWeather;
    String lastAditionalWeatherPath = "/data/user/0/com.example.wojciechpelka.astroweather/files/" + "lastAditionalWeather.bin";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.additional_wather_information_layout,container,false);

        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        visibilityValue = (TextView)rootView.findViewById(R.id.visibilityValue);
        humidityValue = (TextView)rootView.findViewById(R.id.humidityValue);
        windSpeedValue = (TextView)rootView.findViewById(R.id.windForceValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        windDirectionValue = (TextView)rootView.findViewById(R.id.windDirectionValue);

        if(ApplicationSettings.getIsConnectedToNetwerk())
        {
            service = new YahooWeatherService(this);
            service.refreashWeather(ApplicationSettings.getSettings().getCity()+", "+ApplicationSettings.getSettings().getCountry());
        }
        else
        {
            lastAdditionalWeather = (LastAdditionalWeather) Serializer.Deserialize(lastAditionalWeatherPath);

            if(lastAdditionalWeather==null)
            {
                lastAdditionalWeather = new LastAdditionalWeather("Loading...",44,"Loading...","Loading...","Loading...","Loading...");

            }

            if(isAdded())
            {
                int resource = getResources().getIdentifier("drawable/weather"+lastAdditionalWeather.getLastCode(),null,getActivity().getPackageName());
                Drawable weatherIconDrawable = getResources().getDrawable(resource,null);
                weatherImage.setImageDrawable(weatherIconDrawable);

            }

            titleValue.setText(lastAdditionalWeather.getLastCity());
            visibilityValue.setText(lastAdditionalWeather.getLastVisibility());
            humidityValue.setText(lastAdditionalWeather.getLastHumidity());
            windSpeedValue.setText(lastAdditionalWeather.getLastWindSpeed());
            windDirectionValue.setText(lastAdditionalWeather.getLastWindDirection());
        }

        return rootView;
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

        windSpeedValue.setText(UnitsConverter.speed(String.valueOf(chanel.getWind().getWindSpeed())));
        windDirectionValue.setText(chanel.getWind().getWindDirecetion());
        humidityValue.setText(chanel.getAtmosphere().getHumidity());
        visibilityValue.setText(chanel.getAtmosphere().getVisibility());
        titleValue.setText(chanel.getLocation().getCity());

        LastAdditionalWeather lastAdditionalWeather = new LastAdditionalWeather(titleValue.getText().toString(),item.getCondition().getCode(),windDirectionValue.getText().toString(),windSpeedValue.getText().toString(),humidityValue.getText().toString(),visibilityValue.getText().toString());
        Serializer.Serialize(lastAdditionalWeather,lastAditionalWeatherPath);
    }

    @Override
    public void serviceFailure(Exception ex)
    {
        if(isAdded()) {
            Toast.makeText(getContext(), "0 RESULTS!", Toast.LENGTH_LONG).show();
        }
    }
}
