package com.example.android.popularmovies.common.moviedb.json.converters;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

public interface ConvertFromJsonObject<T> {

    T convertFromJsonObject(final JSONObject jsonObject) throws JSONException;

}
