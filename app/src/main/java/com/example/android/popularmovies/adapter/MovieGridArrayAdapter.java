package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.MovieDetailActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieGridArrayAdapter extends RecyclerView.Adapter<MovieGridArrayAdapter.MoviePosterViewHolder> {

    private List<MovieListItem> movieListItems;

    public MovieGridArrayAdapter(final List<MovieListItem> movieListItems) {
        this.movieListItems = movieListItems;
    }

    @Override
    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        final int movieItemLayout = R.layout.item_grid_list;
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(movieItemLayout, parent, false);

        final MoviePosterViewHolder moviePosterViewHolder = new MoviePosterViewHolder(view);

        return moviePosterViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviePosterViewHolder holder, int position) {
        holder.bind(movieListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return movieListItems.size();
    }

    public class MoviePosterViewHolder extends RecyclerView.ViewHolder {

        private ImageView posterImageView;

        public MoviePosterViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);
        }

        public void bind(final MovieListItem movieListItem) {
            Picasso.with(posterImageView.getContext())
                    .load("http://image.tmdb.org/t/p/w185/" + movieListItem.getPosterUrl())
                    .into(posterImageView);

            posterImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                    intent.putExtra(MovieDbConstants.MOVIE_POSTER_PATH, movieListItem.getPosterUrl());
                    intent.putExtra(MovieDbConstants.MOVIE_ORIGINAL_TITLE, movieListItem.getTitle());
                    intent.putExtra(MovieDbConstants.MOVIE_RELEASE_DATE, movieListItem.getReleaseDate());
                    intent.putExtra(MovieDbConstants.MOVIE_VOTE_AVERAGE, movieListItem.getVoteAverage());
                    intent.putExtra(MovieDbConstants.MOVIE_OVERVIEW, movieListItem.getOverview());

                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
