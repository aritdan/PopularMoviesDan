package com.example.android.popularmovies.grid.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.adapter.AbstractMoviePosterViewHolder;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.activity.MovieDetailActivity;
import com.example.android.popularmovies.grid.model.MovieListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public class MoviePosterViewHolder extends AbstractMoviePosterViewHolder {

    private static final String TAG = MoviePosterViewHolder.class.getName();

    public MoviePosterViewHolder(View itemView) {
        super(itemView);
        Log.d(TAG, "CREATE MoviePosterViewHolder()");
    }

    @Override
    protected void showImage(MovieListItem movieListItem) {
        Picasso.with(posterImageView.getContext())
                .load("http://image.tmdb.org/t/p/w185/" + movieListItem.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterImageView);
    }
}
