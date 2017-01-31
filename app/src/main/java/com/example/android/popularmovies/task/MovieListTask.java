package com.example.android.popularmovies.task;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.moviedb.http.JsonConverter;
import com.example.android.popularmovies.moviedb.http.MovieDataDAO;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieListTask extends AsyncTask<Void, Void, List<MovieListItem>> {

    private RecyclerView movieGridRecyclerView;

    private String contentType;
    private String orderType;

    public MovieListTask(final RecyclerView movieGridRecyclerView, final String contentType,
                         final String orderType) {
        this.movieGridRecyclerView = movieGridRecyclerView;
        this.contentType = contentType;
        this.orderType = orderType;
    }

    @Override
    protected List<MovieListItem> doInBackground(Void... voids) {
        final MovieDataDAO movieDataDAO = new MovieDataDAO();
        final String urlResult = movieDataDAO.getMovieList(contentType, orderType);
        final List<MovieListItem> movieListItems = JsonConverter.getMovieListItemsFromJsonString(urlResult);
        return movieListItems;
    }

    @Override
    protected void onPostExecute(List<MovieListItem> movieListItems) {
        super.onPostExecute(movieListItems);
        if (movieListItems == null || movieListItems.isEmpty()) {
            Toast.makeText(movieGridRecyclerView.getContext(), R.string.movie_list_error, Toast.LENGTH_LONG)
                    .show();
        } else {
            movieGridRecyclerView.setAdapter(new MovieGridArrayAdapter(movieListItems));
        }
    }
}
