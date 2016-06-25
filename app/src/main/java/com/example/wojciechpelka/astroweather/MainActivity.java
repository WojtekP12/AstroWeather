package com.example.wojciechpelka.astroweather;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
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
    TextView Lat, Lng;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeviceSettings.config = getResources().getConfiguration();

        CurrentTime.SetCurrentDate();

        initCurrentTimeControls();

        SetApplicationClock();

        initCurrentLocationControls();

        setCurrentLocationControls();

        initMainViewPager();

        setMainViewPager();
    }

    private void setMainViewPager() {
        if(pager!=null)
        {
            pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            pager .setAdapter(pagerAdapter);
        }
    }

    private void initMainViewPager() {
        pager = (ViewPager)findViewById(R.id.pager);
    }

    private void setCurrentLocationControls() {
        Lat.setText(String.valueOf(CurrentLocalization.lat));
        Lng.setText(String.valueOf(CurrentLocalization.lng));
    }

    private void initCurrentLocationControls() {
        Lat = (TextView)findViewById(R.id.currentLocationLat);
        Lng = (TextView)findViewById(R.id.currentLocationLng);
    }

    private void initCurrentTimeControls() {
        Hours = (TextView)findViewById(R.id.currentTimeHours);
        Minutes = (TextView)findViewById(R.id.currentTimeMinutes);
        Seconds = (TextView)findViewById(R.id.currentTimeSeconds);
    }

    private void SetApplicationClock() {
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
                                CurrentTime.setHour(hours);
                                CurrentTime.setMinute(minutes);
                                CurrentTime.setSecond(seconds);
                                Hours.setText(TimeFormatter.format(CurrentTime.getHour()) + "");
                                Minutes.setText(TimeFormatter.format(CurrentTime.getMinute())+"");
                                Seconds.setText(TimeFormatter.format(CurrentTime.getSecond())+"");
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }


    @Override
    public void onBackPressed()
    {
        if(pager.getCurrentItem() == 0)
        {
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

    public boolean onOptionsItemSelected(MenuItem item)
    {
        final Context context = this;
        //Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_exit:
            {
                System.exit(0);
                return true;
            }
            case R.id.action_settings:
            {
                Intent intent = new Intent(context, SettingsActivity.class);
                finish();
                startActivity(intent);

            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_BACK:
                System.exit(0);
                return true;
        }

        return super.onKeyDown(keycode, e);
    }

}
