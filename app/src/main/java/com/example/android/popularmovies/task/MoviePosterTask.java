package com.example.android.popularmovies.task;

import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

public class MoviePosterTask extends AsyncTask<String, Void, Void> {

    private ImageView posterImageView;

    public MoviePosterTask(final ImageView posterImageView) {
        this.posterImageView = posterImageView;
    }

    @Override
    protected Void doInBackground(String... strings) {

        return null;
    }
}
