package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieGridArrayAdapter extends RecyclerView.Adapter<MovieGridArrayAdapter.MoviePosterViewHolder> {

    private List<String> moviePosterUrlList;

    public MovieGridArrayAdapter(final List<String> moviePosterUrlList) {
        this.moviePosterUrlList = moviePosterUrlList;
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
        holder.bind(moviePosterUrlList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviePosterUrlList.size();
    }

    public class MoviePosterViewHolder extends RecyclerView.ViewHolder {

        private ImageView posterImageView;

        public MoviePosterViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster);
        }

        public void bind(final String value) {
            Picasso.with(posterImageView.getContext()).load("http://image.tmdb.org/t/p/w185/" + value)
                    .into(posterImageView);
        }
    }
}
