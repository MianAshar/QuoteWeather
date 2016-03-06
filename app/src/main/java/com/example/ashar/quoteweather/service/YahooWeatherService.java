package com.example.ashar.quoteweather.service;


import android.net.Uri;
import android.os.AsyncTask;

import com.example.ashar.quoteweather.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ashar on 01/02/2016.
 */
public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private  String Location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return Location;
    }

    public void refreshWeather(String loc){
        this.Location = loc;
    new AsyncTask<String, Void, String>() {
        @Override
        protected String doInBackground(String... params) {

            String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u = 'c'", params[0]);
            String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

            try {
                URL url = new URL(endpoint);
                URLConnection connection = url.openConnection();
                InputStream inputStream= connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    result.append(line);
                }

                return result.toString();

            }
            catch (Exception e) {
                error = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s == null && error != null){
                callback.serviceFailure(error);
                return;
            }

            try {
                JSONObject data = new JSONObject(s);
                JSONObject queryResults= data.optJSONObject("query");
                int count =  queryResults.optInt("count");
                if(count == 0){
                    callback.serviceFailure(new LocationWeatherException("No weather information found for "+ Location));
                }
                Channel channel = new Channel();
                channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                callback.serviceSuccess(channel);
            } catch (JSONException e) {
                callback.serviceFailure(e);

            }

        }
    }.execute(Location);
    }


    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }

}
