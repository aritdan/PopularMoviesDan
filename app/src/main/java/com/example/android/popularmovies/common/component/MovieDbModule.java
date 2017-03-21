package com.example.android.popularmovies.common.component;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

@Module
public class MovieDbModule {

    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

}
