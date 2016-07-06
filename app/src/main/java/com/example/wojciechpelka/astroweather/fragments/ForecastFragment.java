package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wojciechpelka.astroweather.LastBasicWeather;
import com.example.wojciechpelka.astroweather.serialization.Serializer;
import com.example.wojciechpelka.astroweather.settings.ApplicationSettings;
import com.example.wojciechpelka.astroweather.LastForecast;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.converters.UnitsConverter;
import com.example.wojciechpelka.astroweather.data.Chanel;
import com.example.wojciechpelka.astroweather.data.ForecastDay;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.service.WeatherServiceCallback;
import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-25.
 */

public class ForecastFragment  extends Fragment implements WeatherServiceCallback
{
    private YahooWeatherService service;

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

    String lastForecastPath = "/data/user/0/com.example.wojciechpelka.astroweather/files/" + "lastForecast.bin";
    LastForecast lastForecast;

    List<ForecastDay> list;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.foreacast_layout,container,false);

        InitForecastDaysControls(rootView);

        titleValue = (TextView)rootView.findViewById(R.id.titleValue);


        if(ApplicationSettings.getIsConnectedToNetwerk())
        {
            service = new YahooWeatherService(this);
            service.refreashWeather(ApplicationSettings.getSettings().getCity()+", "+ApplicationSettings.getSettings().getCountry());
        }
        else
        {
            SetLastWeather();
            titleValue.setText(lastForecast.getLastCity());
        }

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



    //LAST WEATHER
    private void SetLastWeather() {
        getLastForecast();
        setWeekLastWeatherImages();
        setWeekLastWeather();

    }
    private void setWeekLastWeather() {
        setDay1Weather(getDayName_LastForecast(1), getDayTemperature_LastForecast(1));
        setDay2Weather(getDayName_LastForecast(2), getDayTemperature_LastForecast(2));
        setDay3Weather(getDayName_LastForecast(3), getDayTemperature_LastForecast(3));
        setDay4Weather(getDayName_LastForecast(4), getDayTemperature_LastForecast(4));
        setDay5Weather(getDayName_LastForecast(5), getDayTemperature_LastForecast(5));
        setDay6Weather(getDayName_LastForecast(6), getDayTemperature_LastForecast(6));
        setDay7Weather(getDayName_LastForecast(7), getDayTemperature_LastForecast(7));
    }
    private String getDayName_LastForecast(int day) {
        return String.valueOf(lastForecast.getLastForecastDays().get(day-1).getDay());
    }
    private String getDayTemperature_LastForecast(int day) {
        return UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(day-1).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(day-1).getHigh()));
    }
    private void setWeekLastWeatherImages() {
        if(isAdded())
        {
            day1WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(1)));
            day2WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(2)));
            day3WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(3)));
            day4WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(4)));
            day5WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(5)));
            day6WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(6)));
            day7WeatherImage.setImageDrawable(getWeatherDrawable(getLastWeatherCodeforDay(7)));
        }
    }
    private int getLastWeatherCodeforDay(int day) {
        return lastForecast.getLastForecastDays().get(day-1).getCode();
    }
    private void getLastForecast() {
        lastForecast = (LastForecast)Serializer.Deserialize(lastForecastPath);
        if(lastForecast==null)
        {
            ForecastDay day = new ForecastDay(44,"Loading...","Loading...","0","0","Loading...");
            List<ForecastDay> lastForecastDays = new ArrayList<>();
            for(int i=0;i<7;i++)
            {
                lastForecastDays.add(day);
            }
            lastForecast = new LastForecast(lastForecastDays,"Loading...");
        }
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
    @NonNull
    private List<ForecastDay> fillSerializableForecastList() {
        List<ForecastDay> lastForecastDays = new ArrayList<>();

        ForecastDay day1 = new ForecastDay(list.get(0).getCode(),"",day1DayValue.getText().toString(),list.get(0).getHigh(),list.get(0).getLow(),"");
        ForecastDay day2 = new ForecastDay(list.get(1).getCode(),"",day2DayValue.getText().toString(),list.get(1).getHigh(),list.get(1).getLow(),"");
        ForecastDay day3 = new ForecastDay(list.get(2).getCode(),"",day3DayValue.getText().toString(),list.get(2).getHigh(),list.get(2).getLow(),"");
        ForecastDay day4 = new ForecastDay(list.get(3).getCode(),"",day4DayValue.getText().toString(),list.get(3).getHigh(),list.get(3).getLow(),"");
        ForecastDay day5 = new ForecastDay(list.get(4).getCode(),"",day5DayValue.getText().toString(),list.get(4).getHigh(),list.get(4).getLow(),"");
        ForecastDay day6 = new ForecastDay(list.get(5).getCode(),"",day6DayValue.getText().toString(),list.get(5).getHigh(),list.get(5).getLow(),"");
        ForecastDay day7 = new ForecastDay(list.get(6).getCode(),"",day7DayValue.getText().toString(),list.get(6).getHigh(),list.get(6).getLow(),"");

        lastForecastDays.add(day1);
        lastForecastDays.add(day2);
        lastForecastDays.add(day3);
        lastForecastDays.add(day4);
        lastForecastDays.add(day5);
        lastForecastDays.add(day6);
        lastForecastDays.add(day7);
        return lastForecastDays;
    }




    @Override
    public void serviceSuccess(Chanel chanel)
    {
        Item item = chanel.getItem();
        list = item.getDayList();
        titleValue.setText(chanel.getLocation().getCity());

        SetForecast();

        List<ForecastDay> lastForecastDays = fillSerializableForecastList();
        lastForecast = new LastForecast(lastForecastDays,titleValue.getText().toString());

        Serializer.Serialize(lastForecast,lastForecastPath);

    }

    @Override
    public void serviceFailure(Exception ex)
    {

    }


}