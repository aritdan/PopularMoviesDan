package com.example.android.popularmovies.favorite.loader.grid;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.popularmovies.favorite.adapter.FavoriteGridArrayAdapter;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan.ariton on 21-Mar-17.
 */

public class FavoritesGridLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FavoritesGridLoaderCallback.class.getName();

    public static final int LOADER_ID = 151;

    private Context context;
    private RecyclerView movieGridRecyclerView;

    public FavoritesGridLoaderCallback(final Context context, final RecyclerView movieGridRecyclerView) {
        this.context = context;
        this.movieGridRecyclerView = movieGridRecyclerView;
        Log.d(TAG, "CREATE FavoritesGridLoaderCallback()");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "]");
        final Uri uri = FavoritesContract.FavoritesEntry.CONTENT_URI;
        final CursorLoader loader = new CursorLoader(context,
                uri,
                null,
                null,
                null,
                null);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "ENTER onLoadFinished()");

        final List<MovieListItem> movieItemList = new ArrayList<>();
        if (data != null) {
            final String basePath = context.getFilesDir().getPath() + "/";
            while (data.moveToNext()) {
                final MovieListItem movieListItem = new MovieListItem();
                movieListItem.setFavorite(true);
                movieListItem.setMovieId(data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID)));
                movieListItem.setTitle(data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_TITLE)));
                movieListItem.setReleaseDate(data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE)));
                movieListItem.setVoteAverage(data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE)));
                movieListItem.setOverview(data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW)));
                movieListItem.setPosterPath(basePath +
                        data.getString(data.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_POSTER)));
                movieItemList.add(movieListItem);
            }

            movieGridRecyclerView.setAdapter(new FavoriteGridArrayAdapter(movieItemList));
        }


        Log.d(TAG, "EXIT onLoadFinished()");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
