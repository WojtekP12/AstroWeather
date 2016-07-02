package com.example.wojciechpelka.astroweather.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-26.
 */
public class Item implements JSONPopulator
{
    private Condition condition;
    private String lat;
    private String lng;

    private List<ForecastDay> dayList = new ArrayList<ForecastDay>();

    private JSONArray forecast;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<ForecastDay> getDayList() {
        return dayList;
    }

    @Override
    public void populate(JSONObject data) throws JSONException {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        lat = data.optString("lat");
        lng = data.optString("long");
        forecast = data.optJSONArray("forecast");
        generateForecastDayList(forecast);
    }

    private void generateForecastDayList(JSONArray forecast) throws JSONException
    {
        for(int i=0;i<forecast.length();i++)
        {
            JSONObject dayObject = forecast.getJSONObject(i);
            ForecastDay day = new ForecastDay(dayObject.getInt("code"), dayObject.getString("date"), dayObject.getString("day"), dayObject.getString("high"), dayObject.getString("low"), dayObject.getString("text"));
            dayList.add(day);
        }
    }

    public class ForecastDay
    {
        private int code;
        private String date;
        private String day;
        private String high;
        private String low;
        private String text;

        public ForecastDay(int code, String date, String day, String high, String low, String text) {
            this.code = code;
            this.date = date;
            this.day = day;
            this.high = high;
            this.low = low;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getDate() {
            return date;
        }

        public String getDay() {
            return day;
        }

        public String getHigh() {
            return high;
        }

        public String getLow() {
            return low;
        }

        public String getText() {
            return text;
        }
    }
}
