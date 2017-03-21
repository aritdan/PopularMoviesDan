package com.example.android.popularmovies.details.loader.reviews;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.model.MovieReview;
import com.example.android.popularmovies.favorite.model.FavoriteModel;

import java.util.List;

/**
 * Created by dan.ariton on 16-Mar-17.
 */

public class MovieDbReviewLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<MovieReview>> {

    private static final String TAG = MovieDbReviewLoaderCallbacks.class.getName();

    private Context context;
    private LinearLayout reviewLinearLayout;
    private FavoriteModel favoriteModel;

    public MovieDbReviewLoaderCallbacks(final Context context, final LinearLayout reviewLinearLayout,
                                        final FavoriteModel favoriteModel) {
        this.context = context;
        this.reviewLinearLayout = reviewLinearLayout;
        this.favoriteModel = favoriteModel;
        Log.d(TAG, "CREATED MovieDbReviewLoaderCallbacks");
    }

    @Override
    public Loader<List<MovieReview>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "], args=[" + args + "]");
        final String movieId = args.getString(MovieDbConstants.KEY_MOVIE_ID);
        final MovieDbReviewLoader loader = new MovieDbReviewLoader(context, movieId);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieReview>> loader, List<MovieReview> data) {
        Log.d(TAG, "ENTER onLoadFinished() data=[" + data + "]");
        reviewLinearLayout.removeAllViews();
        if (data != null && !data.isEmpty()) {
            favoriteModel.setMovieReviewList(data);
            for (int i = 0; i < data.size(); i++) {
                final MovieReview movieReview = data.get(i);
                final View movieReviewView = LayoutInflater.from(context).inflate(R.layout.item_review,
                        reviewLinearLayout, false);

                final TextView authorTextView = (TextView) movieReviewView.findViewById(R.id.tv_review_author);
                authorTextView.setText(movieReview.getAuthor());

                final TextView contentTextView = (TextView) movieReviewView.findViewById(R.id.tv_review_content);
                contentTextView.setText(movieReview.getContent());

                reviewLinearLayout.addView(movieReviewView);

                if (i < data.size() - 1) {
                    reviewLinearLayout.addView(LayoutInflater.from(context)
                    .inflate(R.layout.horizontal_separator, reviewLinearLayout, false));
                }
            }
        }
        Log.d(TAG, "EXIT onLoadFinished()");
    }

    @Override
    public void onLoaderReset(Loader<List<MovieReview>> loader) {

    }
}
