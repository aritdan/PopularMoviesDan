package com.example.android.popularmovies.task;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.android.popularmovies.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.moviedb.enums.ContentTypeEnum;
import com.example.android.popularmovies.moviedb.enums.OrderTypeEnum;
import com.example.android.popularmovies.moviedb.http.JsonConverter;
import com.example.android.popularmovies.moviedb.http.MovieDataDAO;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieListTask extends AsyncTask<Void, Void, List<MovieListItem>> {

    private RecyclerView movieGridRecyclerView;

    private ContentTypeEnum contentTypeEnum;
    private OrderTypeEnum orderTypeEnum;

    public MovieListTask(final RecyclerView movieGridRecyclerView, final ContentTypeEnum contentTypeEnum,
                         final OrderTypeEnum orderTypeEnum) {
        this.movieGridRecyclerView = movieGridRecyclerView;
        this.contentTypeEnum = contentTypeEnum;
        this.orderTypeEnum = orderTypeEnum;
    }

    @Override
    protected List<MovieListItem> doInBackground(Void... voids) {
        final MovieDataDAO movieDataDAO = new MovieDataDAO();
        final String urlResult = movieDataDAO.getPopularMovieImgLocation(contentTypeEnum, orderTypeEnum);
        final List<MovieListItem> movieListItems = JsonConverter.getMovieListItemsFromJsonString(urlResult);
        return movieListItems;
    }

    @Override
    protected void onPostExecute(List<MovieListItem> movieListItems) {
        super.onPostExecute(movieListItems);
        movieGridRecyclerView.setAdapter(new MovieGridArrayAdapter(movieListItems));
    }
}
