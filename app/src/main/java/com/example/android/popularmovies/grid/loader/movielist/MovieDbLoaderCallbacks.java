package com.example.android.popularmovies.grid.loader.movielist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.adapter.AbstractMovieGridArrayAdapter;
import com.example.android.popularmovies.grid.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.common.moviedb.json.converters.ConvertMovieListItemFromJson;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 27-Feb-17.
 */

public class MovieDbLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<MovieListItem>> {

    private static final String TAG = MovieDbLoaderCallbacks.class.getName();

    private Context context;

    private RecyclerView movieGridRecyclerView;
    private boolean loaded = false;

    public MovieDbLoaderCallbacks(final Context context,
                                  final RecyclerView movieGridRecyclerView) {
        Log.d(TAG, "CREATE MovieDbLoaderCallbacks");
        this.context = context;
        this.movieGridRecyclerView = movieGridRecyclerView;
    }


    @Override
    public Loader<List<MovieListItem>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader()");
        final String contentType = args.getString(MovieDbConstants.KEY_CONTENT_TYPE, MovieDbConstants.CONTENT_TYPE_MOVIE);
        final String orderType = args.getString(MovieDbConstants.KEY_ORDER_TYPE, MovieDbConstants.ORDER_TYPE_POPULAR);
        final MovieDbLoader loader = new MovieDbLoader(context, contentType, orderType);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieListItem>> loader, List<MovieListItem> data) {
        Log.d(TAG, "ENTER onLoadFinished()");
        if (!loaded) {
            if (data == null || data.isEmpty()) {
                Toast.makeText(movieGridRecyclerView.getContext(), R.string.movie_list_error, Toast.LENGTH_LONG)
                        .show();
            } else {
                if (movieGridRecyclerView.getAdapter() instanceof MovieGridArrayAdapter) {
                    ((MovieGridArrayAdapter) movieGridRecyclerView.getAdapter()).changeMovieList(data);
                } else {
                    movieGridRecyclerView.setAdapter(new MovieGridArrayAdapter(data));
                }
            }
            loaded = true;
        }
        Log.d(TAG, "EXIT onLoadFinished()");
    }

    @Override
    public void onLoaderReset(Loader<List<MovieListItem>> loader) {
        Log.d(TAG, "ENTER onLoaderReset()");
        ((AbstractMovieGridArrayAdapter) movieGridRecyclerView.getAdapter()).changeMovieList(Collections.EMPTY_LIST);
        Log.d(TAG, "EXIT onLoaderReset()");
    }

}
