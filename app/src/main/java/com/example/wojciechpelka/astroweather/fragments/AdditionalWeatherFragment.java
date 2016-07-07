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

import com.example.wojciechpelka.astroweather.data.WeatherData;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.converters.UnitsConverter;
import com.example.wojciechpelka.astroweather.data.Channel;
import com.example.wojciechpelka.astroweather.data.Item;

/**
 * Created by Wojciech on 2016-06-25.
 */
public class AdditionalWeatherFragment extends Fragment
{
    ImageView weatherImage;
    TextView visibilityValue;
    TextView humidityValue;
    TextView titleValue;
    TextView windSpeedValue;
    TextView windDirectionValue;
    private ProgressDialog dialog;

    ViewGroup rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = (ViewGroup)inflater.inflate(R.layout.additional_wather_information_layout,container,false);
        initControls();
        fillControls(WeatherData.channel);
        return rootView;
    }

    private void initControls() {
        weatherImage = (ImageView)rootView.findViewById(R.id.weatherImage);
        visibilityValue = (TextView)rootView.findViewById(R.id.visibilityValue);
        humidityValue = (TextView)rootView.findViewById(R.id.humidityValue);
        windSpeedValue = (TextView)rootView.findViewById(R.id.windForceValue);
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        windDirectionValue = (TextView)rootView.findViewById(R.id.windDirectionValue);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fillControls(Channel channel)
    {
        if(channel == null)
        {
            return;
        }

        Item item = channel.getItem();

        if(isAdded())
        {
            int resource = getResources().getIdentifier("drawable/weather"+item.getCondition().getCode(),null,getActivity().getPackageName());

            Drawable weatherIconDrawable = getResources().getDrawable(resource,null);

            weatherImage.setImageDrawable(weatherIconDrawable);
        }

        windSpeedValue.setText(UnitsConverter.speed(String.valueOf(channel.getWind().getWindSpeed())));
        windDirectionValue.setText(channel.getWind().getWindDirecetion());
        humidityValue.setText(channel.getAtmosphere().getHumidity());
        visibilityValue.setText(channel.getAtmosphere().getVisibility());
        titleValue.setText(channel.getLocation().getCity());
    }

    @Override
    public void onResume()
    {
        rootView.invalidate();
        fillControls(WeatherData.channel);
        super.onResume();
    }

}
