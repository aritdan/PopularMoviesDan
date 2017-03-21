package com.example.android.popularmovies.common.component;

import com.example.android.popularmovies.common.moviedb.json.converters.ConvertMovieListItemFromJson;
import com.example.android.popularmovies.common.moviedb.json.converters.ConvertMovieReviewFromJsonObject;
import com.example.android.popularmovies.common.moviedb.json.converters.ConvertMovieVideoDescriptorFromJsonObject;
import com.example.android.popularmovies.common.loader.LoaderUtilityWrapper;

import dagger.Component;

/**
 * Created by dan.ariton on 27-Feb-17.
 */

@LoaderScope
@Component(modules = {MovieDbModule.class})
public interface MovieLoaderComponent {

    void inject(LoaderUtilityWrapper loaderUtilityWrapper);
    ConvertMovieListItemFromJson getConvertMovieListItemFromJson();
    ConvertMovieVideoDescriptorFromJsonObject getConvertMovieVideoDescriptorFromJsonObject();
    ConvertMovieReviewFromJsonObject getConvertMovieReviewFromJsonObject();

}
