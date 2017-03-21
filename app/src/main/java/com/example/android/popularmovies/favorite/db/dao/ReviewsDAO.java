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
public class ReviewsDAO {

    private static final String TAG = ReviewsDAO.class.getName();

    private static final String[] ALL_COLUMNS = new String[] {FavoritesContract.ReviewsEntry._ID,
            FavoritesContract.ReviewsEntry.COLUMN_MOVIE,
            FavoritesContract.ReviewsEntry.COLUMN_AUTHOR,
            FavoritesContract.ReviewsEntry.COLUMN_CONTENT,
            FavoritesContract.ReviewsEntry.COLUMN_URL};

    private FavoritesDbHelper favoritesDbHelper;

    @Inject
    public ReviewsDAO(final FavoritesDbHelper favoritesDbHelper) {
        this.favoritesDbHelper = favoritesDbHelper;
    }

    public long insertNewReview(final ContentValues reviewData) {
        Log.d(TAG, "ENTER insertNewReview() movieData=[" + reviewData + "]");
        final long newId = favoritesDbHelper.getWritableDatabase()
                .insert(FavoritesContract.ReviewsEntry.TABLE_NAME,
                    null, reviewData);
        Log.d(TAG, "EXIT insertNewReview() newId=[" + newId + "]");
        return newId;
    }

    public int deleteReviewByMovieId(final String movieId) {
        Log.d(TAG, "ENTER deleteReviewByMovieId() movieId=[" + movieId + "]");
        final int deleteCount = favoritesDbHelper.getWritableDatabase()
                .delete(FavoritesContract.ReviewsEntry.TABLE_NAME,
                        FavoritesContract.ReviewsEntry.COLUMN_MOVIE + "=?",
                        new String[] {movieId});
        Log.d(TAG, "EXIT deleteReviewByMovieId() deleteCount=[" + deleteCount + "]");
        return deleteCount;
    }

    public Cursor getReviewsByMovieId(final String movieId) {
        Log.d(TAG, "ENTER getReviewsByMovieId() movieId=[" + movieId + "]");
        final Cursor result = favoritesDbHelper.getReadableDatabase()
                .query(FavoritesContract.ReviewsEntry.TABLE_NAME,
                        ALL_COLUMNS,
                        FavoritesContract.ReviewsEntry.COLUMN_MOVIE + "=?",
                        new String[] {movieId},
                        null,
                        null,
                        null);
        Log.d(TAG, "EXIT getReviewsByMovieId()");
        return result;
    }

}
