package com.example.android.popularmovies.favorite.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
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
public class VideosDAO {

    private static final String TAG = VideosDAO.class.getName();

    private static final String[] ALL_COLUMNS = new String[] {FavoritesContract.VideosEntry._ID,
            FavoritesContract.VideosEntry.COLUMN_MOVIE,
            FavoritesContract.VideosEntry.COLUMN_TITLE,
            FavoritesContract.VideosEntry.COLUMN_KEY,
            FavoritesContract.VideosEntry.COLUMN_SITE};

    private FavoritesDbHelper favoritesDbHelper;

    @Inject
    public VideosDAO(final FavoritesDbHelper favoritesDbHelper) {
        this.favoritesDbHelper = favoritesDbHelper;
    }

    public void insertNewVideo(final ContentValues videoData) {
        Log.d(TAG, "ENTER insertNewVideo() videosData=[" + videoData + "]");
        final long newId = favoritesDbHelper.getWritableDatabase()
                .insert(FavoritesContract.VideosEntry.TABLE_NAME,
                null, videoData);
        Log.d(TAG, "EXIT insertNewVideo() newId=[" + newId + "]");
    }

    public int deleteVideoByMovieId(final String movieId) {
        Log.d(TAG, "ENTER deleteVideoByMovieId() movieId=[" + movieId + "]");
        final int deleteCount = favoritesDbHelper.getWritableDatabase()
                .delete(FavoritesContract.VideosEntry.TABLE_NAME,
                        FavoritesContract.VideosEntry.COLUMN_MOVIE + "=?",
                        new String[] {movieId});
        Log.d(TAG, "EXIT deleteVideoByMovieId() deleteCount=[" + deleteCount + "]");
        return deleteCount;
    }

    public Cursor getVideosByMovieId(final String movieId) {
        Log.d(TAG, "ENTER getVideosByMovieId() movieId=[" + movieId + "]");
        final Cursor result = favoritesDbHelper.getReadableDatabase()
                .query(FavoritesContract.VideosEntry.TABLE_NAME,
                        ALL_COLUMNS,
                        FavoritesContract.VideosEntry.COLUMN_MOVIE + "=?",
                        new String[] {movieId},
                        null,
                        null,
                        null);
        Log.d(TAG, "EXIT getVideosByMovieId()");
        return result;
    }

}
