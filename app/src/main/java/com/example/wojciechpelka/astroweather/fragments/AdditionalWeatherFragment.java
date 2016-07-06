package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
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

    ViewGroup rootView;

    LastAdditionalWeather lastAdditionalWeather;
    String lastAditionalWeatherPath = "/data/user/0/com.example.wojciechpelka.astroweather/files/" + "lastAditionalWeather.bin";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.additional_wather_information_layout,container,false);

        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        visibilityValue = (TextView)rootView.findViewById(R.id.visibilityValue);
        humidityValue = (TextView)rootView.findViewById(R.id.humidityValue);
        windSpeedValue = (TextView)rootView.findViewById(R.id.windForceValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        windDirectionValue = (TextView)rootView.findViewById(R.id.windDirectionValue);
        setHasOptionsMenu(true);

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

    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
