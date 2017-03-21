package com.example.android.popularmovies.common.moviedb.json;

import android.util.Log;

import com.example.android.popularmovies.common.component.LoaderScope;
import com.example.android.popularmovies.common.moviedb.json.converters.ConvertFromJsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

@LoaderScope
public class JsonConverter {

    @Inject
    public JsonConverter() {}

    private static final String TAG = JsonConverter.class.getName();

    public <T> List<T> getItemListFromJson(final String json,
                                                       final ConvertFromJsonObject<T> converter) {
        Log.d(TAG, "ENTER getItemListFromJson() json=[" + json + "]");
        final List<T> listItems = new ArrayList<>();

        try {
            if (json != null && !json.trim().equals("")) {

                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    listItems.add(converter.convertFromJsonObject(jsonArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            Log.d(TAG, "ERROR:", e);
        }

        Log.d(TAG, "EXIT getItemListFromJson() listItems=[" + listItems + "]");
        return listItems;
    }
}
