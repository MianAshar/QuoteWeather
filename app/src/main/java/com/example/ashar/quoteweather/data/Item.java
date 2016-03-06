package com.example.ashar.quoteweather.data;

import org.json.JSONObject;

/**
 * Created by Ashar on 01/02/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();

        condition.populate(data.optJSONObject("condition")); //("condition") is from weather JSON file
    }
}
