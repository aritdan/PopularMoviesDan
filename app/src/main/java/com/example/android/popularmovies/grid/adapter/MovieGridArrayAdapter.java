package com.example.android.popularmovies.grid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.common.adapter.AbstractMovieGridArrayAdapter;
import com.example.android.popularmovies.common.adapter.AbstractMoviePosterViewHolder;
import com.example.android.popularmovies.details.activity.MovieDetailActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.grid.model.MovieListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieGridArrayAdapter extends AbstractMovieGridArrayAdapter {

    private static final String TAG = MovieGridArrayAdapter.class.getName();
    private List<MovieListItem> movieListItems;

    public MovieGridArrayAdapter(final List<MovieListItem> movieListItems) {
        super(movieListItems);
        Log.d(TAG, "CREATE MovieGridArrayAdapter()");
    }

    @Override
    protected AbstractMoviePosterViewHolder getViewHolder(View view) {
        return new MoviePosterViewHolder(view);
    }

}
