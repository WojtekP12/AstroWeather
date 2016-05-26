package com.example.wojciechpelka.astroweather;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.astrocalculator.AstroCalculator;

public class MainActivity extends ActionBarActivity
{
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            super.onBackPressed();
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
