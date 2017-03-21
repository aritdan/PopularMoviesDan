package com.example.android.popularmovies.details.loader.videos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;
import com.example.android.popularmovies.favorite.model.FavoriteModel;

import java.util.List;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public class MovieDbVideosLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<MovieVideoDescriptor>> {

    private static final String TAG = MovieDbVideosLoaderCallbacks.class.getName();

    private Context context;
    private LinearLayout videoLinearLayout;
    private FavoriteModel favoriteModel;

    public MovieDbVideosLoaderCallbacks(final Context context,
                                        final LinearLayout videoLinearLayout,
                                        final FavoriteModel favoriteModel) {
        Log.d(TAG, "CREATE MovieDbVideosLoaderCallbacks");
        this.context = context;
        this.videoLinearLayout = videoLinearLayout;
        this.favoriteModel = favoriteModel;
    }

    @Override
    public Loader<List<MovieVideoDescriptor>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "ENTER onCreateLoader() id=[" + id + "], args=[" + args + "]");
        final String movieId = args.getString(MovieDbConstants.KEY_MOVIE_ID);
        final MovieDbVideosLoader loader = new MovieDbVideosLoader(context, movieId);
        Log.d(TAG, "EXIT onCreateLoader()");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieVideoDescriptor>> loader, List<MovieVideoDescriptor> data) {
        videoLinearLayout.removeAllViews();
        if (!data.isEmpty()) {
            favoriteModel.setMovieVideoDescriptorList(data);
            for (final MovieVideoDescriptor movieVideoDescriptor : data) {
                final View listItemView = LayoutInflater.from(videoLinearLayout.getContext()).inflate(R.layout.item_video, videoLinearLayout, false);
                final TextView videoTitleTextView = (TextView) listItemView.findViewById(R.id.tv_video_title);
                videoTitleTextView.setText(movieVideoDescriptor.getTitle());
                videoLinearLayout.addView(listItemView);
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String videoKey = movieVideoDescriptor.getKey();
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
    public void onLoaderReset(Loader<List<MovieVideoDescriptor>> loader) {
        //videoListAdapter.refreshData(null);
    }
}
