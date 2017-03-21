package com.example.android.popularmovies.details.loader.reviews;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.popularmovies.common.loader.AbstractMovieDbLoader;
import com.example.android.popularmovies.common.moviedb.json.converters.ConvertMovieReviewFromJsonObject;
import com.example.android.popularmovies.details.model.MovieReview;

import java.util.List;

/**
 * Created by dan.ariton on 15-Mar-17.
 */

public class MovieDbReviewLoader extends AbstractMovieDbLoader<List<MovieReview>> {

    private static final String TAG = MovieDbReviewLoader.class.getName();

    public static final int LOADER_ID = 112;

    private String movieId;

    public MovieDbReviewLoader(Context context, final String movieId) {
        super(context);
        this.movieId = movieId;
        Log.d(TAG, "CREATED MovieDbReviewLoader movieId=[" + this.movieId + "]");
    }

    @Override
    public List<MovieReview> loadInBackground() {
        Log.d(TAG, "ENTER loadInBackground()");
        final String movieReviewJson = movieDbDataProvider.getMovieReviewList(movieId);
        final List<MovieReview> movieReviewList = jsonConverter.getItemListFromJson(movieReviewJson,
                new ConvertMovieReviewFromJsonObject());
        Log.d(TAG, "EXIT loadInBackground() movieReviewList=[" + movieReviewList + "]");
        return movieReviewList;
    }
}
