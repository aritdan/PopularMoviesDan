package com.example.android.popularmovies.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public abstract class AbstractMovieGridArrayAdapter extends RecyclerView.Adapter<AbstractMoviePosterViewHolder> {

    private static final String TAG = AbstractMovieGridArrayAdapter.class.getName();
    private List<MovieListItem> movieListItems;

    public AbstractMovieGridArrayAdapter(final List<MovieListItem> movieListItems) {
        this.movieListItems = movieListItems;
        Log.d(TAG, "CREATE AbstractMovieGridArrayAdapter()");
    }

    public void changeMovieList(final List<MovieListItem> movieList) {
        Log.d(TAG, "ENTER changeMovieList() movieList=[" + movieList + "]");
        movieListItems = movieList;
        notifyDataSetChanged();
        Log.d(TAG, "EXIT changeMovieList()");
    }

    @Override
    public AbstractMoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "ENTER onCreateViewHolder()");
        final Context context = parent.getContext();

        final int movieItemLayout = R.layout.item_grid_list;
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(movieItemLayout, parent, false);

        Log.d(TAG, "EXIT onCreateViewHolder()");
        return getViewHolder(view);
    }

    protected abstract AbstractMoviePosterViewHolder getViewHolder(final View view);

    @Override
    public void onBindViewHolder(AbstractMoviePosterViewHolder holder, int position) {
        Log.d(TAG, "ENTER onBindViewHolder()");
        holder.bind(movieListItems.get(position));
        Log.d(TAG, "EXIT onBindViewHolder()");
    }

    @Override
    public int getItemCount() {
        return movieListItems.size();
    }

}
