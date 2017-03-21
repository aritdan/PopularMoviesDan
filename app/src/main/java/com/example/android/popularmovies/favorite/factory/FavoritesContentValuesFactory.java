package com.example.android.popularmovies.favorite.factory;

import android.content.ContentValues;

import com.example.android.popularmovies.favorite.component.FavoritesScope;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 20-Mar-17.
 */

@FavoritesScope
public class FavoritesContentValuesFactory {

    @Inject
    public FavoritesContentValuesFactory() {}

    public ContentValues getFavoritesContentValues(final ContentValues values) {
        final ContentValues favoritesContentValues = new ContentValues();

        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_TITLE,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_TITLE));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW));
        favoritesContentValues.put(FavoritesContract.FavoritesEntry.COLUMN_POSTER,
                values.getAsString(FavoritesContract.FavoritesEntry.COLUMN_POSTER));

        return favoritesContentValues;
    }

    public ContentValues getVideoContentValues(final ContentValues values, final long movieId, final int index) {
        final ContentValues videoContentValues = new ContentValues();

        videoContentValues.put(FavoritesContract.VideosEntry.COLUMN_MOVIE, String.valueOf(movieId));
        videoContentValues.put(FavoritesContract.VideosEntry.COLUMN_TITLE,
                values.getAsString(FavoritesContract.VideosEntry.COLUMN_TITLE + "_" + index));
        videoContentValues.put(FavoritesContract.VideosEntry.COLUMN_KEY,
                values.getAsString(FavoritesContract.VideosEntry.COLUMN_KEY + "_" + index));
        videoContentValues.put(FavoritesContract.VideosEntry.COLUMN_SITE,
                values.getAsString(FavoritesContract.VideosEntry.COLUMN_SITE + "_" + index));

        return videoContentValues;
    }

    public ContentValues getReviewContentValues(final ContentValues values, final long movieId, final int index) {
        final ContentValues reviewContentValues = new ContentValues();

        reviewContentValues.put(FavoritesContract.ReviewsEntry.COLUMN_MOVIE, String.valueOf(movieId));
        reviewContentValues.put(FavoritesContract.ReviewsEntry.COLUMN_AUTHOR,
                values.getAsString(FavoritesContract.ReviewsEntry.COLUMN_AUTHOR + "_" + index));
        reviewContentValues.put(FavoritesContract.ReviewsEntry.COLUMN_CONTENT,
                values.getAsString(FavoritesContract.ReviewsEntry.COLUMN_CONTENT + "_" + index));
        reviewContentValues.put(FavoritesContract.ReviewsEntry.COLUMN_URL,
                values.getAsString(FavoritesContract.ReviewsEntry.COLUMN_URL + "_" + index));

        return reviewContentValues;
    }

}
