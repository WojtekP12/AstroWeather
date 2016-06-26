package com.example.wojciechpelka.astroweather;

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

import com.example.wojciechpelka.astroweather.data.Chanel;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.service.WeatherServiceCallback;
import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

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
        windSpeedValue.setText(chanel.getWind().getWindSpeed() + " " + chanel.getUnits().getSpeed());
        windDirectionValue.setText(chanel.getWind().getWindDirecetion());
        humidityValue.setText(chanel.getAtmosphere().getHumidity());
        visibilityValue.setText(chanel.getAtmosphere().getVisibility());
        titleValue.setText(chanel.getLocation().getCity());

    }

    @Override
    public void serviceFailure(Exception ex)
    {
        Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_LONG);
    }
}
