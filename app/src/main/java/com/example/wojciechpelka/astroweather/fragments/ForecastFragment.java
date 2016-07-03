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

import com.example.wojciechpelka.astroweather.ApplicationSettings;
import com.example.wojciechpelka.astroweather.LastAdditionalWeather;
import com.example.wojciechpelka.astroweather.LastForecast;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.UnitsConverter;
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

    String lastForecastPath = "/data/user/0/com.example.wojciechpelka.astroweather/files/" + "lastForecast.bin";
    LastForecast lastForecast;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup rootView;
        rootView = (ViewGroup)inflater.inflate(R.layout.foreacast_layout,container,false);

        day1WeatherImage = (ImageView)rootView.findViewById(R.id.day1WeatherImage);
        day1DayValue = (TextView)rootView.findViewById(R.id.day1Value);
        day1TemperatureValue = (TextView)rootView.findViewById(R.id.day1TemperatureValue);

        day2WeatherImage = (ImageView)rootView.findViewById(R.id.day2WeatherImage);
        day2DayValue = (TextView)rootView.findViewById(R.id.day2Value);
        day2TemperatureValue = (TextView)rootView.findViewById(R.id.day2TemperatureValue);

        day3WeatherImage = (ImageView)rootView.findViewById(R.id.day3WeatherImage);
        day3DayValue = (TextView)rootView.findViewById(R.id.day3Value);
        day3TemperatureValue = (TextView)rootView.findViewById(R.id.day3TemperatureValue);

        day4WeatherImage = (ImageView)rootView.findViewById(R.id.day4WeatherImage);
        day4DayValue = (TextView)rootView.findViewById(R.id.day4Value);
        day4TemperatureValue = (TextView)rootView.findViewById(R.id.day4TemperatureValue);

        day5WeatherImage = (ImageView)rootView.findViewById(R.id.day5WeatherImage);
        day5DayValue = (TextView)rootView.findViewById(R.id.day5Value);
        day5TemperatureValue = (TextView)rootView.findViewById(R.id.day5TemperatureValue);

        day6WeatherImage = (ImageView)rootView.findViewById(R.id.day6WeatherImage);
        day6DayValue = (TextView)rootView.findViewById(R.id.day6Value);
        day6TemperatureValue = (TextView)rootView.findViewById(R.id.day6TemperatureValue);

        day7WeatherImage = (ImageView)rootView.findViewById(R.id.day7WeatherImage);
        day7DayValue = (TextView)rootView.findViewById(R.id.day7Value);
        day7TemperatureValue = (TextView)rootView.findViewById(R.id.day7TemperatureValue);

        if(ApplicationSettings.getIsConnectedToNetwerk())
        {
            service = new YahooWeatherService(this);
            service.refreashWeather(ApplicationSettings.getSettings().getCity()+", "+ApplicationSettings.getSettings().getCountry());
        }
        else
        {
            lastForecast = (LastForecast) Deserialize(lastForecastPath);

            if(lastForecast==null)
            {
                ForecastDay day = new ForecastDay(44,"Loading...","Loading...","0","0","Loading...");
                List<ForecastDay> lastForecastDays = new ArrayList<>();
                for(int i=0;i<7;i++)
                {
                    lastForecastDays.add(day);
                }
                lastForecast = new LastForecast(lastForecastDays);
            }

            if(isAdded())
            {
                int day1Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(0).getCode(),null,getActivity().getPackageName());
                Drawable day1WeatherIconDrawable = getResources().getDrawable(day1Resource,null);
                day1WeatherImage.setImageDrawable(day1WeatherIconDrawable);

                int day2Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(1).getCode(),null,getActivity().getPackageName());
                Drawable day2WeatherIconDrawable = getResources().getDrawable(day2Resource,null);
                day2WeatherImage.setImageDrawable(day2WeatherIconDrawable);

                int day3Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(2).getCode(),null,getActivity().getPackageName());
                Drawable day3WeatherIconDrawable = getResources().getDrawable(day3Resource,null);
                day3WeatherImage.setImageDrawable(day3WeatherIconDrawable);

                int day4Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(3).getCode(),null,getActivity().getPackageName());
                Drawable day4WeatherIconDrawable = getResources().getDrawable(day4Resource,null);
                day4WeatherImage.setImageDrawable(day4WeatherIconDrawable);

                int day5Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(4).getCode(),null,getActivity().getPackageName());
                Drawable day5WeatherIconDrawable = getResources().getDrawable(day5Resource,null);
                day5WeatherImage.setImageDrawable(day5WeatherIconDrawable);

                int day6Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(5).getCode(),null,getActivity().getPackageName());
                Drawable day6WeatherIconDrawable = getResources().getDrawable(day6Resource,null);
                day6WeatherImage.setImageDrawable(day6WeatherIconDrawable);

                int day7Resource = getResources().getIdentifier("drawable/weather"+lastForecast.getLastForecastDays().get(6).getCode(),null,getActivity().getPackageName());
                Drawable day7WeatherIconDrawable = getResources().getDrawable(day7Resource,null);
                day7WeatherImage.setImageDrawable(day7WeatherIconDrawable);
            }

            day1DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(0).getDay()));
            day1TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(0).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(0).getHigh())));

            day2DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(1).getDay()));
            day2TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(1).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(1).getHigh())));

            day3DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(2).getDay()));
            day3TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(2).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(2).getHigh())));

            day4DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(3).getDay()));
            day4TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(3).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(3).getHigh())));

            day5DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(4).getDay()));
            day5TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(4).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(4).getHigh())));

            day6DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(5).getDay()));
            day6TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(5).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(5).getHigh())));

            day7DayValue.setText(String.valueOf(lastForecast.getLastForecastDays().get(6).getDay()));
            day7TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(6).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(lastForecast.getLastForecastDays().get(6).getHigh())));

        }

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void serviceSuccess(Chanel chanel)
    {
        Item item = chanel.getItem();
        List<ForecastDay> list = item.getDayList();

        if(isAdded())
        {
            int day1Resource = getResources().getIdentifier("drawable/weather"+list.get(0).getCode(),null,getActivity().getPackageName());
            Drawable day1WeatherIconDrawable = getResources().getDrawable(day1Resource,null);
            day1WeatherImage.setImageDrawable(day1WeatherIconDrawable);
        }

        day1DayValue.setText(String.valueOf(list.get(0).getDay()));
        day1TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(0).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(0).getHigh())));
        ForecastDay day1 = new ForecastDay(list.get(0).getCode(),"",day1DayValue.getText().toString(),list.get(0).getHigh(),list.get(0).getLow(),"");

        if(isAdded())
        {
            int day2Resource = getResources().getIdentifier("drawable/weather"+list.get(1).getCode(),null,getActivity().getPackageName());
            Drawable day2WeatherIconDrawable = getResources().getDrawable(day2Resource,null);
            day2WeatherImage.setImageDrawable(day2WeatherIconDrawable);
        }


        day2DayValue.setText(String.valueOf(list.get(1).getDay()));
        day2TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(1).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(1).getHigh())));
        ForecastDay day2 = new ForecastDay(list.get(1).getCode(),"",day2DayValue.getText().toString(),list.get(1).getHigh(),list.get(1).getLow(),"");


        if(isAdded())
        {
            int day3Resource = getResources().getIdentifier("drawable/weather"+list.get(2).getCode(),null,getActivity().getPackageName());
            Drawable day3WeatherIconDrawable = getResources().getDrawable(day3Resource,null);
            day3WeatherImage.setImageDrawable(day3WeatherIconDrawable);
        }


        day3DayValue.setText(String.valueOf(list.get(2).getDay()));
        day3TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(2).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(2).getHigh())));
        ForecastDay day3 = new ForecastDay(list.get(2).getCode(),"",day3DayValue.getText().toString(),list.get(2).getHigh(),list.get(2).getLow(),"");

        if(isAdded())
        {
            int day4Resource = getResources().getIdentifier("drawable/weather"+list.get(3).getCode(),null,getActivity().getPackageName());
            Drawable day4WeatherIconDrawable = getResources().getDrawable(day4Resource,null);
            day4WeatherImage.setImageDrawable(day4WeatherIconDrawable);
        }


        day4DayValue.setText(String.valueOf(list.get(3).getDay()));
        day4TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(3).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(3).getHigh())));
        ForecastDay day4 = new ForecastDay(list.get(3).getCode(),"",day4DayValue.getText().toString(),list.get(3).getHigh(),list.get(3).getLow(),"");

        if(isAdded())
        {
            int day5Resource = getResources().getIdentifier("drawable/weather"+list.get(4).getCode(),null,getActivity().getPackageName());
            Drawable day5WeatherIconDrawable = getResources().getDrawable(day5Resource,null);
            day5WeatherImage.setImageDrawable(day5WeatherIconDrawable);
        }


        day5DayValue.setText(String.valueOf(list.get(4).getDay()));
        day5TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(4).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(4).getHigh())));
        ForecastDay day5 = new ForecastDay(list.get(4).getCode(),"",day5DayValue.getText().toString(),list.get(4).getHigh(),list.get(4).getLow(),"");

        if(isAdded())
        {
            int day6Resource = getResources().getIdentifier("drawable/weather"+list.get(5).getCode(),null,getActivity().getPackageName());
            Drawable day6WeatherIconDrawable = getResources().getDrawable(day6Resource,null);
            day6WeatherImage.setImageDrawable(day6WeatherIconDrawable);
        }


        day6DayValue.setText(String.valueOf(list.get(5).getDay()));
        day6TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(5).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(5).getHigh())));
        ForecastDay day6 = new ForecastDay(list.get(5).getCode(),"",day6DayValue.getText().toString(),list.get(5).getHigh(),list.get(5).getLow(),"");

        if(isAdded())
        {
            int day7Resource = getResources().getIdentifier("drawable/weather"+list.get(6).getCode(),null,getActivity().getPackageName());
            Drawable day7WeatherIconDrawable = getResources().getDrawable(day7Resource,null);
            day7WeatherImage.setImageDrawable(day7WeatherIconDrawable);
        }

        day7DayValue.setText(String.valueOf(list.get(6).getDay()));
        day7TemperatureValue.setText(UnitsConverter.temperature(String.valueOf(list.get(6).getLow()))+" - "+UnitsConverter.temperature(String.valueOf(list.get(6).getHigh())));
        ForecastDay day7 = new ForecastDay(list.get(6).getCode(),"",day7DayValue.getText().toString(),list.get(6).getHigh(),list.get(6).getLow(),"");

        List<ForecastDay> lastForecastDays = new ArrayList<>();
        lastForecastDays.add(day1);
        lastForecastDays.add(day2);
        lastForecastDays.add(day3);
        lastForecastDays.add(day4);
        lastForecastDays.add(day5);
        lastForecastDays.add(day6);
        lastForecastDays.add(day7);

        lastForecast = new LastForecast(lastForecastDays);
        Serialize(lastForecast,lastForecastPath);

    }

    @Override
    public void serviceFailure(Exception ex)
    {

    }

    private void Serialize(Object object, String path)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path))); //Select where you wish to save the file...
            oos.writeObject(object); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            oos.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private Object Deserialize(String path)
    {
        try
        {
            return loadClassFile(new File(path));
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public Object loadClassFile(File f)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Object o = ois.readObject();
            return o;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}