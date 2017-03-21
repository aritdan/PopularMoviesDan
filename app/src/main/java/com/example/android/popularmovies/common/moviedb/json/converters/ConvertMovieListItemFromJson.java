package com.example.android.popularmovies.common.moviedb.json.converters;

import android.util.Log;

import com.example.android.popularmovies.common.component.LoaderScope;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.grid.model.MovieListItem;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

@LoaderScope
public class ConvertMovieListItemFromJson implements ConvertFromJsonObject<MovieListItem> {

    private static final String TAG = ConvertMovieListItemFromJson.class.getName();

    @Inject
    public ConvertMovieListItemFromJson() {}

    public MovieListItem convertFromJsonObject(final JSONObject jsonObject) throws JSONException {
        Log.d(TAG, "ENTER convertFromJsonObject() jsonObject=[" + jsonObject + "]");
        final MovieListItem movieListItem = new MovieListItem();

        movieListItem.setMovieId(jsonObject.getString(MovieDbConstants.MOVIE_ID));
        movieListItem.setTitle(jsonObject.getString(MovieDbConstants.MOVIE_ORIGINAL_TITLE));
        movieListItem.setPosterPath(jsonObject.getString(MovieDbConstants.MOVIE_POSTER_PATH));
        movieListItem.setReleaseDate(jsonObject.getString(MovieDbConstants.MOVIE_RELEASE_DATE));
        movieListItem.setVoteAverage(jsonObject.getString(MovieDbConstants.MOVIE_VOTE_AVERAGE));
        movieListItem.setOverview(jsonObject.getString(MovieDbConstants.MOVIE_OVERVIEW));

        Log.d(TAG, "EXIT convertFromJsonObject() movieListItem=[" + movieListItem + "]");
        return movieListItem;
    }

}
