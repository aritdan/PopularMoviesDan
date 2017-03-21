package com.example.android.popularmovies.main.component;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dan.ariton on 27-Feb-17.
 */

@Singleton
@Component()
public interface PopularMoviesComponent {

    void inject(Activity activity);

}
