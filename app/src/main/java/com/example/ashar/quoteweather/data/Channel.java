package com.example.ashar.quoteweather.data;

import org.json.JSONObject;

/**
 * Created by Ashar on 01/02/2016.
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units")); //("units") is from weather JSON file

        item = new Item();
        item.populate(data.optJSONObject("item")); //("item") is from weather JSON file
    }
}
