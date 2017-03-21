package com.example.android.popularmovies.favorite.loader.video;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.android.popularmovies.details.loader.videos.MovieDbVideosLoader;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.model.FavoriteModel;

import java.util.List;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public class FavoriteVideosLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FavoriteVideosLoaderCallbacks.class.getName();
    public static final int LOADER_ID = 153;

    private Context context;
    private LinearLayout videoLinearLayout;


    public FavoriteVideosLoaderCallbacks(final Context context,
                                         final LinearLayout videoLinearLayout) {
        Log.d(TAG, "CREATE FavoriteVideosLoaderCallbacks");
        this.context = context;
        this.videoLinearLayout = videoLinearLayout;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "], args=[" + args + "]");
        final String movieId = args.getString(MovieDbConstants.KEY_MOVIE_ID);
        final Uri videosUri = FavoritesContract.VideosEntry.MOVIE_CONTENT_URI.buildUpon()
                .appendPath(movieId).build();
        final CursorLoader loader = new CursorLoader(context,
                videosUri,
                null,
                null,
                null,
                null);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        videoLinearLayout.removeAllViews();
        if (data != null) {
            while (data.moveToNext()) {
                final View listItemView = LayoutInflater.from(videoLinearLayout.getContext()).inflate(R.layout.item_video, videoLinearLayout, false);
                final TextView videoTitleTextView = (TextView) listItemView.findViewById(R.id.tv_video_title);
                videoTitleTextView.setText(data.getString(data.getColumnIndex(FavoritesContract.VideosEntry.COLUMN_TITLE)));
                videoLinearLayout.addView(listItemView);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String videoKey = data.getString(data.getColumnIndex(FavoritesContract.VideosEntry.COLUMN_KEY));
                        final String url = "https://www.youtube.com/watch?v=" + videoKey;
                        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                        if (intent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        videoLinearLayout.removeAllViews();
    }
}
