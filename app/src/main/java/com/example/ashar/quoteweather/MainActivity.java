package com.example.ashar.quoteweather;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashar.quoteweather.data.Channel;
import com.example.ashar.quoteweather.data.Item;
import com.example.ashar.quoteweather.service.WeatherServiceCallback;
import com.example.ashar.quoteweather.service.YahooWeatherService;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity implements WeatherServiceCallback {
    private TextView tempTextView, locationTextView, conditionTextView ;
    private ImageView weatherIcon;
    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        tempTextView = (TextView) findViewById(R.id.temp);
        locationTextView = (TextView) findViewById(R.id.cityName);
        conditionTextView = (TextView) findViewById(R.id.condition);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Lahore, Pakistan");



    }
    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();

        //int resourceId = getResources().getIdentifier("drawable/icon_ " + item.getCondition().getCode(), null, getPackageName());
        //@SuppressWarnings("deprecation")
        //Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
       // weatherIcon.setImageDrawable(weatherIconDrawable);
        locationTextView.setText(service.getLocation());
        tempTextView.setText(item.getCondition().getTemprature()+ "\u00b0"+channel.getUnits().getTemperature() );
        conditionTextView.setText(item.getCondition().getDescription());

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        dialog.hide();
    }
}
