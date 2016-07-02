package com.example.wojciechpelka.astroweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SettingsActivity extends AppCompatActivity {

    String speedUnit;
    String tempUnit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText latValue = (EditText) findViewById(R.id.LatValue);
        final EditText lngValue = (EditText) findViewById(R.id.LngValue);
        final EditText refreshRate = (EditText) findViewById(R.id.refreshRateValue);
        final EditText countryValue = (EditText) findViewById(R.id.countryValue);
        final EditText cityValue = (EditText) findViewById(R.id.cityValue);
        final RadioButton cValue = (RadioButton) findViewById(R.id.cValue);
        final RadioButton fValue = (RadioButton) findViewById(R.id.fValue);
        final RadioButton kmhValue = (RadioButton) findViewById(R.id.kmhValue);
        final RadioButton mphValue = (RadioButton) findViewById(R.id.mphValue);

        latValue.setText(String.valueOf(ApplicationSettings.getSettings().getLat()));
        lngValue.setText(String.valueOf(ApplicationSettings.getSettings().getLng()));
        refreshRate.setText(String.valueOf(ApplicationSettings.getSettings().getRefresh()));
        countryValue.setText(String.valueOf(ApplicationSettings.getSettings().getCountry()));
        cityValue.setText(String.valueOf(ApplicationSettings.getSettings().getCity()));

        SetRadioButtonsControls(cValue, fValue, kmhValue, mphValue);

        Button saveButton = (Button)findViewById(R.id.saveButton);
        assert saveButton != null;

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    CurrentLocalization.lat = Double.parseDouble(latValue.getText().toString());
                    CurrentLocalization.lng = Double.parseDouble(lngValue.getText().toString());
                    int refresh = Integer.parseInt(refreshRate.getText().toString());
                    String country = countryValue.getText().toString();
                    String city = cityValue.getText().toString();

                    if(tempUnit == null && speedUnit == null)
                    {
                        setUnits(cValue, fValue, kmhValue, mphValue);
                    }

                    String tUnit = tempUnit;
                    String sUnit = speedUnit;

                    Settings settings = new Settings(CurrentLocalization.lat,CurrentLocalization.lng,refresh,city,country,sUnit,tUnit);

                    ApplicationSettings.setSettings(settings);

                    SerializeSettings();

                    finish();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
                catch(NumberFormatException ex)
                {
                    finish();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setUnits(RadioButton cValue, RadioButton fValue, RadioButton kmhValue, RadioButton mphValue) {
        if(cValue.isChecked())
        {
            tempUnit = "C";
        }
        if(fValue.isChecked())
        {
            tempUnit = "F";
        }
        if(kmhValue.isChecked())
        {
            speedUnit = "kmh";
        }
        if(mphValue.isChecked())
        {
            speedUnit = "mph";
        }
    }


    private void SetRadioButtonsControls(RadioButton cValue, RadioButton fValue, RadioButton kmhValue, RadioButton mphValue)
    {
        if (ApplicationSettings.getSettings().getSpeedUnit().contains("kmh"))
        {
            kmhValue.setChecked(true);
            mphValue.setChecked(false);
        }

        if (ApplicationSettings.getSettings().getSpeedUnit().contains("mph"))
        {
            kmhValue.setChecked(false);
            mphValue.setChecked(true);
        }

        if (ApplicationSettings.getSettings().getDegreesUnit().contains("F"))
        {
            fValue.setChecked(true);
            cValue.setChecked(false);
        }

        if (ApplicationSettings.getSettings().getDegreesUnit().contains("C"))
        {
            fValue.setChecked(false);
            cValue.setChecked(true);
        }
    }

    private void SerializeSettings()
    {
        try
        {
            String path = getFilesDir().getAbsolutePath() + File.separator + "app_settings.bin";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path))); //Select where you wish to save the file...
            oos.writeObject(ApplicationSettings.getSettings()); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            oos.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId())
        {
            case R.id.kmhValue:
                if (checked)
                {
                    speedUnit = "kmh";
                    break;
                }
            case R.id.mphValue:
                if (checked)
                {
                    speedUnit = "mph";
                    break;
                }
            case R.id.fValue:
                if (checked)
                {
                    tempUnit = "F";
                    break;
                }
            case R.id.cValue:
                if (checked)
                {
                    tempUnit = "C";
                    break;
                }
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onKeyDown(keycode, e);
    }
}
