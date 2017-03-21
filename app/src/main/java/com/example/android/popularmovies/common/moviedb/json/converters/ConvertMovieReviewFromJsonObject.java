package com.example.android.popularmovies.common.moviedb.json.converters;

import android.util.Log;

import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.model.MovieReview;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

public class ConvertMovieReviewFromJsonObject implements ConvertFromJsonObject<MovieReview> {

    private static final String TAG = ConvertMovieReviewFromJsonObject.class.getName();

    @Inject
    public ConvertMovieReviewFromJsonObject() {}

    public MovieReview convertFromJsonObject(final JSONObject jsonObject) throws JSONException {
        Log.d(TAG, "ENTER convertFromJsonObject() jsonObject=[" + jsonObject + "]");
        final MovieReview movieReview = new MovieReview();

        movieReview.setAuthor(jsonObject.getString(MovieDbConstants.REVIEW_AUTHOR));
        movieReview.setContent(jsonObject.getString(MovieDbConstants.REVIEW_CONTENT));
        movieReview.setUrl(jsonObject.getString(MovieDbConstants.REVIEW_URL));

        Log.d(TAG, "EXIT convertFromJsonObject() movieReview=[" + movieReview + "]");
        return movieReview;
    }

}
