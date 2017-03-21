package com.example.android.popularmovies.common.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.popularmovies.common.component.DaggerMovieLoaderComponent;
import com.example.android.popularmovies.common.component.MovieLoaderComponent;
import com.example.android.popularmovies.common.moviedb.json.JsonConverter;
import com.example.android.popularmovies.common.moviedb.http.MovieDbDataProvider;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public abstract class AbstractMovieDbLoader<D> extends AsyncTaskLoader<D> {

    protected MovieDbDataProvider movieDbDataProvider;
    protected JsonConverter jsonConverter;
    protected MovieLoaderComponent movieLoaderComponent;

    public AbstractMovieDbLoader(Context context) {
        super(context);
        final LoaderUtilityWrapper wrapper = new LoaderUtilityWrapper();

        movieLoaderComponent = DaggerMovieLoaderComponent.create();
        movieLoaderComponent.inject(wrapper);

        movieDbDataProvider = wrapper.getMovieDbDataProvider();
        jsonConverter = wrapper.getJsonConverter();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
