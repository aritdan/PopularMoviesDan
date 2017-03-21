package com.example.android.popularmovies.common.moviedb.json.converters;

import android.util.Log;

import com.example.android.popularmovies.common.component.LoaderScope;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

@LoaderScope
public class ConvertMovieVideoDescriptorFromJsonObject implements ConvertFromJsonObject<MovieVideoDescriptor> {

    private static final String TAG = ConvertMovieVideoDescriptorFromJsonObject.class.getName();

    @Inject
    public ConvertMovieVideoDescriptorFromJsonObject() {}

    public MovieVideoDescriptor convertFromJsonObject(final JSONObject jsonObject) throws JSONException {
        Log.d(TAG, "ENTER convertFromJsonObject() jsonObject=[" + jsonObject + "]");
        final MovieVideoDescriptor movieVideoDescriptor = new MovieVideoDescriptor();
        movieVideoDescriptor.setId(jsonObject.getString(MovieDbConstants.VIDEO_ID));
        movieVideoDescriptor.setTitle(jsonObject.getString(MovieDbConstants.VIDEO_TITLE));
        movieVideoDescriptor.setKey(jsonObject.getString(MovieDbConstants.VIDEO_KEY));
        movieVideoDescriptor.setSite(jsonObject.getString(MovieDbConstants.VIDEO_SITE));

        Log.d(TAG, "EXIT convertFromJsonObject() movieVideoDescriptor=[" + movieVideoDescriptor + "]");
        return movieVideoDescriptor;
    }

}
