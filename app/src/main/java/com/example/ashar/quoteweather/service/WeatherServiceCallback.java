package com.example.ashar.quoteweather.service;

import com.example.ashar.quoteweather.data.Channel;

/**
 * Created by Ashar on 01/02/2016.
 */
public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
