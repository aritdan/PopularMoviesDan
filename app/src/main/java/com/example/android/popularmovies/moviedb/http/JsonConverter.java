package com.example.android.popularmovies.moviedb.http;

import android.util.Log;

import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

public class JsonConverter {

    private static final String TAG = JsonConverter.class.getName();

    public static List<MovieListItem> getMovieListItemsFromJsonString(final String listJson) {
        Log.d(TAG, "ENTER getMovieListItemsFromJsonString()");
        Log.d(TAG, "JSON OF LIST IS:");
        Log.d(TAG, listJson);
        final List<MovieListItem> movieListItems = new ArrayList<>();

        try {
            final JSONObject movieDb = new JSONObject(listJson);
            final JSONArray movieJsonArray = movieDb.getJSONArray("results");

            for (int i = 0; i < movieJsonArray.length(); i++) {
                movieListItems.add(getMovieListItemFromJsonObject(movieJsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.d(TAG, "ERROR:", e);
        }

        return movieListItems;
    }

    private static MovieListItem getMovieListItemFromJsonObject(final JSONObject movieJsonObject) {
        final MovieListItem movieListItem = new MovieListItem();

        try {
            movieListItem.setMovieId(movieJsonObject.getString(MovieDbConstants.MOVIE_ID));
            movieListItem.setTitle(movieJsonObject.getString(MovieDbConstants.MOVIE_ORIGINAL_TITLE));
            movieListItem.setPosterUrl(movieJsonObject.getString(MovieDbConstants.MOVIE_POSTER_PATH));
            movieListItem.setReleaseDate(movieJsonObject.getString(MovieDbConstants.MOVIE_RELEASE_DATE));
            movieListItem.setVoteAverage(movieJsonObject.getString(MovieDbConstants.MOVIE_VOTE_AVERAGE));
            movieListItem.setOverview(movieJsonObject.getString(MovieDbConstants.MOVIE_OVERVIEW));
        } catch (JSONException e) {
            Log.d(TAG, "ERROR:", e);
        }

        return movieListItem;
    }
}
