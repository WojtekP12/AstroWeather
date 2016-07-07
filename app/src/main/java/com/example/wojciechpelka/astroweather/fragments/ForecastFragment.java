package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
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
import com.example.wojciechpelka.astroweather.data.ForecastDay;
import com.example.wojciechpelka.astroweather.data.Item;

import java.util.List;

/**
 * Created by Wojciech on 2016-06-25.
 */

public class ForecastFragment  extends Fragment
{
    //private YahooWeatherService service;

    ImageView day1WeatherImage;
    TextView day1DayValue;
    TextView day1TemperatureValue;

    ImageView day2WeatherImage;
    TextView day2DayValue;
    TextView day2TemperatureValue;

    ImageView day3WeatherImage;
    TextView day3DayValue;
    TextView day3TemperatureValue;

    ImageView day4WeatherImage;
    TextView day4DayValue;
    TextView day4TemperatureValue;

    ImageView day5WeatherImage;
    TextView day5DayValue;
    TextView day5TemperatureValue;

    ImageView day6WeatherImage;
    TextView day6DayValue;
    TextView day6TemperatureValue;

    ImageView day7WeatherImage;
    TextView day7DayValue;
    TextView day7TemperatureValue;

    TextView titleValue;

    List<ForecastDay> list;
    ViewGroup rootView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = (ViewGroup)inflater.inflate(R.layout.foreacast_layout,container,false);
        setHasOptionsMenu(true);
        InitForecastDaysControls(rootView);
        fillControls(WeatherData.channel);
        return rootView;
    }

    //GENERAL METHODS
    private void setDay7Weather(String dayName, String dayTemperature) {
        day7DayValue.setText(dayName);
        day7TemperatureValue.setText(dayTemperature);
    }
    private void setDay6Weather(String dayName, String dayTemperature) {
        day6DayValue.setText(dayName);
        day6TemperatureValue.setText(dayTemperature);
    }
    private void setDay5Weather(String dayName, String dayTemperature) {
        day5DayValue.setText(dayName);
        day5TemperatureValue.setText(dayTemperature);
    }
    private void setDay4Weather(String dayName, String dayTemperature) {
        day4DayValue.setText(dayName);
        day4TemperatureValue.setText(dayTemperature);
    }
    private void setDay3Weather(String dayName, String dayTemperature) {
        day3DayValue.setText(dayName);
        day3TemperatureValue.setText(dayTemperature);
    }
    private void setDay2Weather(String dayName, String dayTemperature) {
        day2DayValue.setText(dayName);
        day2TemperatureValue.setText(dayTemperature);
    }
    private void setDay1Weather(String dayName, String dayTemperature) {
        day1DayValue.setText(dayName);
        day1TemperatureValue.setText(dayTemperature);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getWeatherDrawable(int weatherCode) {
        int day1Resource = getResources().getIdentifier("drawable/weather"+weatherCode,null,getActivity().getPackageName());
        Drawable drawable = getResources().getDrawable(day1Resource,null);
        return drawable;
    }

    //INIT
    private void InitForecastDaysControls(ViewGroup rootView) {
        initDay1Controls(rootView);

        initDay2Controls(rootView);

        initDay3Controls(rootView);

        initDay4Controls(rootView);

        initDay5Controls(rootView);

        initDay6Controls(rootView);

        initDay7Controls(rootView);
    }
    private void initDay7Controls(ViewGroup rootView) {
        day7WeatherImage = (ImageView)rootView.findViewById(R.id.day7WeatherImage);
        day7DayValue = (TextView)rootView.findViewById(R.id.day7Value);
        day7TemperatureValue = (TextView)rootView.findViewById(R.id.day7TemperatureValue);
    }
    private void initDay6Controls(ViewGroup rootView) {
        day6WeatherImage = (ImageView)rootView.findViewById(R.id.day6WeatherImage);
        day6DayValue = (TextView)rootView.findViewById(R.id.day6Value);
        day6TemperatureValue = (TextView)rootView.findViewById(R.id.day6TemperatureValue);
    }
    private void initDay5Controls(ViewGroup rootView) {
        day5WeatherImage = (ImageView)rootView.findViewById(R.id.day5WeatherImage);
        day5DayValue = (TextView)rootView.findViewById(R.id.day5Value);
        day5TemperatureValue = (TextView)rootView.findViewById(R.id.day5TemperatureValue);
    }
    private void initDay4Controls(ViewGroup rootView) {
        day4WeatherImage = (ImageView)rootView.findViewById(R.id.day4WeatherImage);
        day4DayValue = (TextView)rootView.findViewById(R.id.day4Value);
        day4TemperatureValue = (TextView)rootView.findViewById(R.id.day4TemperatureValue);
    }
    private void initDay3Controls(ViewGroup rootView) {
        day3WeatherImage = (ImageView)rootView.findViewById(R.id.day3WeatherImage);
        day3DayValue = (TextView)rootView.findViewById(R.id.day3Value);
        day3TemperatureValue = (TextView)rootView.findViewById(R.id.day3TemperatureValue);
    }
    private void initDay2Controls(ViewGroup rootView) {
        day2WeatherImage = (ImageView)rootView.findViewById(R.id.day2WeatherImage);
        day2DayValue = (TextView)rootView.findViewById(R.id.day2Value);
        day2TemperatureValue = (TextView)rootView.findViewById(R.id.day2TemperatureValue);
    }
    private void initDay1Controls(ViewGroup rootView) {
        day1WeatherImage = (ImageView)rootView.findViewById(R.id.day1WeatherImage);
        day1DayValue = (TextView)rootView.findViewById(R.id.day1Value);
        day1TemperatureValue = (TextView)rootView.findViewById(R.id.day1TemperatureValue);
    }

    //ACTUAL WEATHER
    private void setWeekWeatherImages() {
        day1WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(1)));
        day2WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(2)));
        day3WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(3)));
        day4WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(4)));
        day5WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(5)));
        day6WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(6)));
        day7WeatherImage.setImageDrawable(getWeatherDrawable(getWeatherCodeforDay(7)));
    }
    private int getWeatherCodeforDay(int day)
    {
        return list.get(day-1).getCode();
    }
    private String getDayName(int day)
    {
        return String.valueOf(list.get(day-1).getDay());
    }
    private String getDayTemperature(int day) {
        return UnitsConverter.temperature(String.valueOf(list.get(day-1).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(day-1).getHigh()));
    }
    private void setWeekWeather(){
        setDay1Weather(getDayName(1), getDayTemperature(1));
        setDay2Weather(getDayName(2), getDayTemperature(2));
        setDay3Weather(getDayName(3), getDayTemperature(3));
        setDay4Weather(getDayName(4), getDayTemperature(4));
        setDay5Weather(getDayName(5), getDayTemperature(5));
        setDay6Weather(getDayName(6), getDayTemperature(6));
        setDay7Weather(getDayName(7), getDayTemperature(7));
    }
    private void SetForecast() {
        setWeekWeatherImages();
        setWeekWeather();
    }

    public void fillControls(Channel channel)
    {
        if(channel == null)
        {
            return;
        }

        Item item = channel.getItem();
        list = item.getDayList();
        titleValue = (TextView)rootView.findViewById(R.id.titleValue);
        titleValue.setText(channel.getLocation().getCity());
        SetForecast();
    }

    @Override
    public void onResume()
    {
        rootView.invalidate();
        fillControls(WeatherData.channel);
        super.onResume();
    }
}