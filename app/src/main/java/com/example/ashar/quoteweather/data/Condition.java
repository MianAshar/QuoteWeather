package com.example.ashar.quoteweather.data;

import org.json.JSONObject;

/**
 * Created by Ashar on 01/02/2016.
 */
public class Condition implements JSONPopulator {
    private  int code;
    private int temprature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemprature() {
        return temprature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code"); //("code") is from weather JSON file
        temprature = data.optInt("temp"); //("temp") is from weather JSON file
        description = data.optString("text"); //("text") is from weather JSON file
    }
}
