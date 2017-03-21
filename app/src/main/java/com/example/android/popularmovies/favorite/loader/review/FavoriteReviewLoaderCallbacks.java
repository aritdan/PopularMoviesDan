package com.example.android.popularmovies.favorite.loader.review;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.loader.reviews.MovieDbReviewLoader;
import com.example.android.popularmovies.details.model.MovieReview;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan.ariton on 16-Mar-17.
 */

public class FavoriteReviewLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FavoriteReviewLoaderCallbacks.class.getName();

    public static final int LOADER_ID = 152;

    private Context context;
    private LinearLayout reviewLinearLayout;

    public FavoriteReviewLoaderCallbacks(final Context context, final LinearLayout reviewLinearLayout) {
        this.context = context;
        this.reviewLinearLayout = reviewLinearLayout;
        Log.d(TAG, "CREATED FavoriteReviewLoaderCallbacks()");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "], args=[" + args + "]");
        final String movieId = args.getString(MovieDbConstants.KEY_MOVIE_ID);
        final Uri reviewUri = FavoritesContract.ReviewsEntry.MOVIE_CONTENT_URI.buildUpon()
                .appendPath(movieId).build();
        final CursorLoader loader = new CursorLoader(context, reviewUri, null, null, null,
                null);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "ENTER onLoadFinished() data=[" + data + "]");
        reviewLinearLayout.removeAllViews();
        if (data != null) {
            while (data.moveToNext()) {
                final View movieReviewView = LayoutInflater.from(context).inflate(R.layout.item_review,
                        reviewLinearLayout, false);

                final TextView authorTextView = (TextView) movieReviewView.findViewById(R.id.tv_review_author);
                authorTextView.setText(data.getString(data.getColumnIndex(FavoritesContract.ReviewsEntry.COLUMN_AUTHOR)));

                final TextView contentTextView = (TextView) movieReviewView.findViewById(R.id.tv_review_content);
                contentTextView.setText(data.getString(data.getColumnIndex(FavoritesContract.ReviewsEntry.COLUMN_CONTENT)));

                reviewLinearLayout.addView(movieReviewView);

                if (data.getPosition() < data.getCount() - 1) {
                    reviewLinearLayout.addView(LayoutInflater.from(context)
                            .inflate(R.layout.horizontal_separator, reviewLinearLayout, false));
                }
            }
        }
        Log.d(TAG, "EXIT onLoadFinished()");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        reviewLinearLayout.removeAllViews();
    }
}
