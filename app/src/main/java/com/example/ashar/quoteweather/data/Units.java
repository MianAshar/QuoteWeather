package com.example.ashar.quoteweather.data;

import org.json.JSONObject;

/**
 * Created by Ashar on 01/02/2016.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature"); //("temperature") is from weather JSON file

    }
}
