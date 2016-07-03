package com.example.wojciechpelka.astroweather.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wojciechpelka.astroweather.ApplicationSettings;
import com.example.wojciechpelka.astroweather.CurrentTime;
import com.example.wojciechpelka.astroweather.DeviceSettings;
import com.example.wojciechpelka.astroweather.R;
import com.example.wojciechpelka.astroweather.ScreenSlidePagerAdapter;
import com.example.wojciechpelka.astroweather.Settings;
import com.example.wojciechpelka.astroweather.TimeFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;

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
        ApplicationSettings.setIsConnectedToNetwerk(isNetworkAvailable());
        if(!ApplicationSettings.getIsConnectedToNetwerk())
        {
            Toast.makeText(this,"No Internet Connection!",Toast.LENGTH_LONG).show();
        }
        ApplicationSettings.path = getFilesDir().getAbsolutePath() + File.separator;

        try
        {
            String path = getFilesDir().getAbsolutePath() + File.separator + "app_settings.bin";
            ApplicationSettings.setSettings((Settings)loadClassFile(new File(path)));
        }
        catch (Exception ex)
        {

        }

        if(ApplicationSettings.getSettings() == null)
        {
            ApplicationSettings.setSettings(new Settings(51.53,-0.24,1,"London","England","mph","F"));

            Intent intent = new Intent(this, SettingsActivity.class);
            finish();
            startActivity(intent);
        }

        DeviceSettings.config = getResources().getConfiguration();

        CurrentTime.SetCurrentDate();

        initCurrentTimeControls();

        SetApplicationClock();

        initCurrentLocationControls();

        setCurrentLocationControls();

        initMainViewPager();

        setMainViewPager();
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
        Lat.setText(String.valueOf(ApplicationSettings.getSettings().getLat()));
        Lng.setText(String.valueOf(ApplicationSettings.getSettings().getLng()));
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
                                int hours = c.get(Calendar.HOUR_OF_DAY);
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
                return true;
            }
            case R.id.action_refresh:
            {
                Intent intent = new Intent(context, MainActivity.class);
                finish();
                startActivity(intent);
                return true;
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

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
