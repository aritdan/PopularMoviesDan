package com.example.android.popularmovies.common.loader;

import com.example.android.popularmovies.common.moviedb.json.JsonConverter;
import com.example.android.popularmovies.common.moviedb.http.MovieDbDataProvider;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public class LoaderUtilityWrapper {

    protected MovieDbDataProvider movieDbDataProvider;
    protected JsonConverter jsonConverter;

    public MovieDbDataProvider getMovieDbDataProvider() {
        return movieDbDataProvider;
    }

    public JsonConverter getJsonConverter() {
        return jsonConverter;
    }

    @Inject
    public void setMovieDbDataProvider(MovieDbDataProvider movieDbDataProvider) {
        this.movieDbDataProvider = movieDbDataProvider;
    }

    @Inject
    public void setJsonConverter(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

}
