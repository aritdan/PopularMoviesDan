package com.example.android.popularmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by dan.ariton on 27-Feb-17.
 */

public class PopularMoviesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
    }
}
