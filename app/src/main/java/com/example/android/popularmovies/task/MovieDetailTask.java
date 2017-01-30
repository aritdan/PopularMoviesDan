package com.example.android.popularmovies.task;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

public class MovieDetailTask extends AsyncTask<String, Void, Void> {

    private ImageView posterImageView;
    private TextView titleTextView;


    public MovieDetailTask(final ImageView posterImageView) {
        this.posterImageView = posterImageView;
    }

    @Override
    protected Void doInBackground(String... strings) {

        return null;
    }
}
