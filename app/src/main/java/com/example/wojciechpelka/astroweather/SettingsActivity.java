package com.example.wojciechpelka.astroweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText latValue = (EditText)findViewById(R.id.LatValue);
                CurrentLocalization.lat = Double.parseDouble(latValue.getText().toString());
                EditText lngValue = (EditText)findViewById(R.id.LngValue);
                CurrentLocalization.lng = Double.parseDouble(lngValue.getText().toString());
                EditText refreshRate = (EditText)findViewById(R.id.refreshRateValue);
                Settings.setRefresh(Integer.parseInt(refreshRate.getText().toString()));
                finish();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
