package com.example.android.popularmovies.favorite.adapter;

import android.view.View;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.adapter.AbstractMoviePosterViewHolder;
import com.example.android.popularmovies.favorite.constants.FavoritesConstants;
import com.example.android.popularmovies.grid.model.MovieListItem;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by dan.ariton on 21-Mar-17.
 */

public class FavoritePosterViewHolder extends AbstractMoviePosterViewHolder {

    public FavoritePosterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void showImage(MovieListItem movieListItem) {
        Picasso.with(posterImageView.getContext())
                .load(new File(movieListItem.getPosterPath()))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterImageView);
    }
}
