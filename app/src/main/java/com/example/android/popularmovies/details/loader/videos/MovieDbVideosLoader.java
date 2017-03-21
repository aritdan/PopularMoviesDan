package com.example.android.popularmovies.details.loader.videos;

import android.content.Context;

import com.example.android.popularmovies.common.loader.AbstractMovieDbLoader;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;

import java.util.List;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public class MovieDbVideosLoader extends AbstractMovieDbLoader<List<MovieVideoDescriptor>> {

    public static final int LOADER_ID = 111;
    private String movieId;

    public MovieDbVideosLoader(Context context, final String movieId) {
        super(context);
        this.movieId = movieId;
    }

    @Override
    public List<MovieVideoDescriptor> loadInBackground() {
        final String movieVideoDescriptorJson = movieDbDataProvider.getMovieVideoList(movieId);
        final List<MovieVideoDescriptor> movieVideoDescriptorList = jsonConverter.getItemListFromJson(
                movieVideoDescriptorJson, movieLoaderComponent.getConvertMovieVideoDescriptorFromJsonObject());
        return movieVideoDescriptorList;
    }

}
