package com.example.android.popularmovies.favorite.loader.detail;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.favorite.constants.FavoritesConstants;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.model.FavoriteModel;

/**
 * Created by dan.ariton on 20-Mar-17.
 */

public class MovieExistsLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MovieExistsLoaderCallbacks.class.getName();

    public static final int LOADER_ID = 150;

    private Context context;

    private ProgressBar favoritesProgressBar;
    private Button favoritesButton;

    private FavoriteModel favoriteModel;

    public MovieExistsLoaderCallbacks(final Context context,
                                      final ProgressBar favoritesProgressBar,
                                      final Button favoritesButton,
                                      final FavoriteModel favoriteModel) {
        this.context = context;
        this.favoritesProgressBar = favoritesProgressBar;
        this.favoritesButton = favoritesButton;
        this.favoriteModel = favoriteModel;
        Log.d(TAG, "CREATE MovieExistsLoaderCallbacks()");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "], args=[" + args + "]");

        favoritesButton.setEnabled(false);
        favoritesProgressBar.setVisibility(View.VISIBLE);

        final String movieId = args.getString(FavoritesConstants.PARAM_MOVIEID);
        final Uri requestUri = FavoritesContract.FavoritesEntry.CONTENT_COUNT_MOVIEID_URI.buildUpon()
                .appendPath(movieId).build();
        final CursorLoader loader = new CursorLoader(context, requestUri, null, null, null, null);

        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "ENTER onLoadFinished()");

        if (data != null && data.moveToFirst()) {
            final Boolean movieExists = Boolean.valueOf(data
                    .getString(data.getColumnIndex(FavoritesConstants.FAVORITE_EXISTS)));

            favoriteModel.setFavorite(movieExists);
            if (movieExists) {
                favoritesButton.setText(context.getText(R.string.detail_favorite_label_off));
            } else {
                favoritesButton.setText(context.getText(R.string.detail_favorite_label));
            }

        }
        favoritesProgressBar.setVisibility(View.INVISIBLE);
        favoritesButton.setEnabled(true);
        Log.d(TAG, "EXIT onLoadFinished()");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // DO NOTHING
    }
}
