package com.example.android.popularmovies.favorite.adapter;

import android.view.View;

import com.example.android.popularmovies.common.adapter.AbstractMovieGridArrayAdapter;
import com.example.android.popularmovies.common.adapter.AbstractMoviePosterViewHolder;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 21-Mar-17.
 */

public class FavoriteGridArrayAdapter extends AbstractMovieGridArrayAdapter {

    public FavoriteGridArrayAdapter(List<MovieListItem> movieListItems) {
        super(movieListItems);
    }

    @Override
    protected AbstractMoviePosterViewHolder getViewHolder(View view) {
        return new FavoritePosterViewHolder(view);
    }
}
