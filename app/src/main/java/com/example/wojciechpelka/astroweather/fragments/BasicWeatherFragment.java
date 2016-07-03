package com.example.wojciechpelka.astroweather.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.wojciechpelka.astroweather.ApplicationSettings;
import com.example.wojciechpelka.astroweather.LastBasicWeather;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.UnitsConverter;
import com.example.wojciechpelka.astroweather.data.Chanel;
import com.example.wojciechpelka.astroweather.data.Item;
import com.example.wojciechpelka.astroweather.service.WeatherServiceCallback;
import com.example.wojciechpelka.astroweather.service.YahooWeatherService;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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

    LastBasicWeather lastBasicWeather;
    String lastBasicWeatherPath = ApplicationSettings.path + "lastBasicWeather.bin";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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

        if(ApplicationSettings.getIsConnectedToNetwerk())
        {
            service = new YahooWeatherService(this);
            service.refreashWeather(ApplicationSettings.getSettings().getCity()+", "+ApplicationSettings.getSettings().getCountry());
        }
        else
        {
            lastBasicWeather = (LastBasicWeather)Deserialize(lastBasicWeatherPath);

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

        titleValue.setText(chanel.getLocation().getCity());
        locationValue.setText(item.getLat() + ", " + item.getLng());
        tempeartureValue.setText(UnitsConverter.temperature(String.valueOf(item.getCondition().getTemperature())));
        timeValue.setText(chanel.getTime());
        airPressureValue.setText(chanel.getAtmosphere().getPressure()+" "+chanel.getUnits().getPressure());
        descriptionValue.setText(item.getCondition().getDescription());

        lastBasicWeather = new LastBasicWeather(locationValue.getText().toString(),timeValue.getText().toString(),tempeartureValue.getText().toString(),airPressureValue.getText().toString(),descriptionValue.getText().toString(),item.getCondition().getCode(),titleValue.getText().toString());
        Serialize(lastBasicWeather,lastBasicWeatherPath);
    }

    @Override
    public void serviceFailure(Exception ex)
    {
        Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_LONG);
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
