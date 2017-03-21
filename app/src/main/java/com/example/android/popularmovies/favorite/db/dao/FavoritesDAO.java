package com.example.android.popularmovies.favorite.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.popularmovies.favorite.component.FavoritesScope;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.db.helper.FavoritesDbHelper;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 20-Mar-17.
 */

@FavoritesScope
public class FavoritesDAO {

    private static final String TAG = FavoritesDAO.class.getName();

    private static final String[] SELECT_ARRAY = {FavoritesContract.FavoritesEntry._ID,
            FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID,
            FavoritesContract.FavoritesEntry.COLUMN_TITLE,
            FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE,
            FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE,
            FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW,
            FavoritesContract.FavoritesEntry.COLUMN_POSTER};

    private FavoritesDbHelper favoritesDbHelper;

    @Inject
    public FavoritesDAO(final FavoritesDbHelper favoritesDbHelper) {
        this.favoritesDbHelper = favoritesDbHelper;
    }

    public long insertNewFavorite(final ContentValues movieData) {
        Log.d(TAG, "ENTER insertNewReview() movieData=[" + movieData + "]");
        final long newId = favoritesDbHelper.getWritableDatabase()
                .insert(FavoritesContract.FavoritesEntry.TABLE_NAME,
                null, movieData);
        Log.d(TAG, "EXIT insertNewReview() newId=[" + newId + "]");
        return newId;
    }

    public int deleteFavorite(final String id) {
        Log.d(TAG, "ENTER deleteFavorite() movieId=[" + id + "]");
        final int deleteCount = favoritesDbHelper.getWritableDatabase()
                .delete(FavoritesContract.FavoritesEntry.TABLE_NAME,
                FavoritesContract.FavoritesEntry._ID + "=?",
                new String[] {id});
        Log.d(TAG, "EXIT deleteFavorite() deleteCount=[" + deleteCount + "]");
        return deleteCount;
    }

    public Cursor getMovieByMovieId(final String movieId) {
        Log.d(TAG, "ENTER getMovieByMovieId() movieId=[" + movieId + "]");
        final Cursor result = favoritesDbHelper.getReadableDatabase()
                .query(FavoritesContract.FavoritesEntry.TABLE_NAME,
                SELECT_ARRAY, FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + "=?",
                new String[] {movieId},
                null,
                null,
                null);
        Log.d(TAG, "EXIT getMovieByMovieId()");
        return result;
    }

    public Cursor getAllMovies() {
        Log.d(TAG, "ENTER getAllMovies()");

        final Cursor result = favoritesDbHelper.getReadableDatabase()
                .query(FavoritesContract.FavoritesEntry.TABLE_NAME,
                        SELECT_ARRAY,
                        null,
                        null,
                        null,
                        null,
                        FavoritesContract.FavoritesEntry._ID);

        Log.d(TAG, "EXIT getAllMovies()");
        return result;
    }

    public boolean getMovieExists(final String movieId) {
        Log.d(TAG, "ENTER getMovieExists() movieId=[" + movieId + "]");
        final long count = DatabaseUtils.queryNumEntries(favoritesDbHelper.getReadableDatabase(),
                FavoritesContract.FavoritesEntry.TABLE_NAME,
                FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + "=?", new String[] {movieId});
        final boolean movieExists = count > 0L;
        Log.d(TAG, "EXIT getMovieExists() movieExists=[" + movieExists + "]");
        return movieExists;
    }


}
