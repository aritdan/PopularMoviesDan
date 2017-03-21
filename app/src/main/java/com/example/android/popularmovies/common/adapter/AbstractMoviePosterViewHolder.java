package com.example.android.popularmovies.common.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.activity.MovieDetailActivity;
import com.example.android.popularmovies.grid.model.MovieListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public abstract class AbstractMoviePosterViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = AbstractMoviePosterViewHolder.class.getName();
    protected ImageView posterImageView;

    public AbstractMoviePosterViewHolder(View itemView) {
        super(itemView);
        posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);
        Log.d(TAG, "CREATE AbstractMoviePosterViewHolder()");
    }

    public void bind(final MovieListItem movieListItem) {
        Log.d(TAG, "ENTER bind() movieListItem=[" + movieListItem + "]");

        showImage(movieListItem);

        posterImageView.setContentDescription(movieListItem.getTitle());

        posterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                intent.putExtra(MovieDbConstants.MOVIE_ITEM, movieListItem);
                view.getContext().startActivity(intent);
            }
        });
        Log.d(TAG, "EXIT bind()");
    }

    protected abstract void showImage(final MovieListItem movieListItem);
}
