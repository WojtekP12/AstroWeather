package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.wojciechpelka.astroweather.data.Channel;
import com.example.wojciechpelka.astroweather.data.WeatherData;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.converters.UnitsConverter;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;

/**
 * Created by Wojciech on 2016-06-25.
 */
public class BasicWeatherFragment extends Fragment {

    ImageView weatherImage;
    TextView locationValue;
    TextView tempeartureValue;
    TextView descriptionValue;
    TextView titleValue;
    TextView timeValue;
    TextView airPressureValue;

    ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = (ViewGroup)inflater.inflate(R.layout.basic_weather_information_layout,container,false);
        initControls();
        fillControls(WeatherData.channel);
        return rootView;
    }

    private void initControls()
    {
        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        locationValue = (TextView)rootView.findViewById(R.id.locationValue);
        tempeartureValue = (TextView)rootView.findViewById(R.id.temperatureValue);
        descriptionValue = (TextView)rootView.findViewById(R.id.descriptionValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        timeValue = (TextView)rootView.findViewById(R.id.timeValue);
        airPressureValue = (TextView)rootView.findViewById(R.id.airPressureValue);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fillControls(Channel channel)
    {
        if(channel == null)
        {
            return;
        }

        Item item  = channel.getItem();;

        if(isAdded())
        {
            int resource = getResources().getIdentifier("drawable/weather"+item.getCondition().getCode(),null,getActivity().getPackageName());
            Drawable weatherIconDrawable = getResources().getDrawable(resource,null);
            weatherImage.setImageDrawable(weatherIconDrawable);
        }

        titleValue.setText(channel.getLocation().getCity());
        locationValue.setText(item.getLat() + ", " + item.getLng());
        tempeartureValue.setText(UnitsConverter.temperature(String.valueOf(item.getCondition().getTemperature())));
        timeValue.setText(channel.getTime());
        airPressureValue.setText(channel.getAtmosphere().getPressure()+" "+ channel.getUnits().getPressure());
        descriptionValue.setText(item.getCondition().getDescription());
    }

    @Override
    public void onResume()
    {
        rootView.invalidate();
        fillControls(WeatherData.channel);
        super.onResume();
    }


}
