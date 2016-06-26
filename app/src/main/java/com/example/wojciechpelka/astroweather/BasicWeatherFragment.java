package com.example.wojciechpelka.astroweather;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wojciechpelka.astroweather.data.Chanel;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.service.WeatherServiceCallback;
import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

import org.w3c.dom.Text;

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

    String temp;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.basic_weather_information_layout,container,false);

        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        locationValue = (TextView)rootView.findViewById(R.id.locationValue);
        tempeartureValue = (TextView)rootView.findViewById(R.id.temperatureValue);
        descriptionValue = (TextView)rootView.findViewById(R.id.descriptionValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        timeValue = (TextView)rootView.findViewById(R.id.timeValue);
        airPressureValue = (TextView)rootView.findViewById(R.id.airPressureValue);

        service = new YahooWeatherService(this);
        service.refreashWeather("London, EN");

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void serviceSuccess(Chanel chanel)
    {
        Item item = chanel.getItem();

        int resource = getResources().getIdentifier("drawable/weather"+item.getCondition().getCode(),null,getActivity().getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resource,null);

        weatherImage.setImageDrawable(weatherIconDrawable);

        titleValue.setText(chanel.getLocation().getCity());
        locationValue.setText(item.getLat() + ", " + item.getLng());
        tempeartureValue.setText(item.getCondition().getTemperature()+" "+chanel.getUnits().getTemperature());
        timeValue.setText(chanel.getTime());
        airPressureValue.setText(chanel.getAtmosphere().getPressure()+" "+chanel.getUnits().getPressure());
        descriptionValue.setText(item.getCondition().getDescription());
    }

    @Override
    public void serviceFailure(Exception ex)
    {
        Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_LONG);
    }
}
