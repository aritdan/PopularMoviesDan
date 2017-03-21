package com.example.android.popularmovies.grid.loader.movielist;

import android.content.Context;
import android.util.Log;

import com.example.android.popularmovies.common.loader.AbstractMovieDbLoader;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 27-Feb-17.
 */

public class MovieDbLoader extends AbstractMovieDbLoader<List<MovieListItem>> {

    private static final String TAG = MovieDbLoader.class.getName();
    public static final int LOADER_ID = 110;

    private String contentType;
    private String orderType;

    public MovieDbLoader(final Context context,
                         final String contentType,
                         final String orderType) {
        super(context);
        Log.d(TAG, "CREATE MovieDbLoader");
        this.contentType = contentType;
        this.orderType = orderType;
    }

    @Override
    public List<MovieListItem> loadInBackground() {
        Log.d(TAG, "ENTER loadInBackground()");
        final String urlResult = movieDbDataProvider.getMovieList(contentType, orderType);
        final List<MovieListItem> movieListItems = jsonConverter.getItemListFromJson(urlResult,
                movieLoaderComponent.getConvertMovieListItemFromJson());
        Log.d(TAG, "EXIT loadInBackground()");
        return movieListItems;
    }

}
