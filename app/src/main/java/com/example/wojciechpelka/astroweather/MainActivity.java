package com.example.wojciechpelka.astroweather;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity
{
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    TextView Hours,Minutes,Seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hours = (TextView)findViewById(R.id.currentTimeHours);
        Minutes = (TextView)findViewById(R.id.currentTimeMinutes);
        Seconds = (TextView)findViewById(R.id.currentTimeSeconds);

        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    while (!isInterrupted())
                    {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable()
                        {
                            Calendar c = Calendar.getInstance();

                            @Override
                            public void run()
                            {
                                int hours = c.get(Calendar.HOUR);
                                int minutes = c.get(Calendar.MINUTE);
                                int seconds = c.get(Calendar.SECOND);
                                CurrentTime.hour = hours;
                                CurrentTime.minute = minutes;
                                CurrentTime.second = seconds;
                                Hours.setText(hours+"");
                                Minutes.setText(minutes+"");
                                Seconds.setText(seconds+"");
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

        TextView Lat = (TextView)findViewById(R.id.currentLocationLat);
        TextView Lng = (TextView)findViewById(R.id.currentLocationLng);

        CurrentLocalization.lat = Double.parseDouble(Lat.getText().toString());
        CurrentLocalization.lng = Double.parseDouble(Lng.getText().toString());

        pager = (ViewPager)findViewById(R.id.pager);

        if(pager!=null)
        {
            pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            pager .setAdapter(pagerAdapter);
        }
    }

    @Override
    public void onBackPressed()
    {
        if(pager.getCurrentItem() == 0)
        {
            //super.onBackPressed();
            pager.setCurrentItem(pager.getCurrentItem());
        }
        else
        {
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


}
