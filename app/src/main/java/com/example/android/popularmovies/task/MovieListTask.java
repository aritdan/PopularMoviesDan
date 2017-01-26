package com.example.android.popularmovies.task;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.android.popularmovies.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.http.MovieDataDAO;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieListTask extends AsyncTask<Void, Void, List<String>> {

    private RecyclerView movieGridRecyclerView;

    public MovieListTask(final RecyclerView movieGridRecyclerView) {
        this.movieGridRecyclerView = movieGridRecyclerView;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        final MovieDataDAO movieDataDAO = new MovieDataDAO();
        final List<String> urlList = movieDataDAO.getPopularMovieImgLocation();
        return urlList;
    }

    @Override
    protected void onPostExecute(List<String> moviePosterUrlList) {
        super.onPostExecute(moviePosterUrlList);
        movieGridRecyclerView.setAdapter(new MovieGridArrayAdapter(moviePosterUrlList));
    }
}
