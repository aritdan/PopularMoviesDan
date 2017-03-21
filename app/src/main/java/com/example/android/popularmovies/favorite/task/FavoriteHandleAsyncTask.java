package com.example.android.popularmovies.favorite.task;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.details.model.MovieReview;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;
import com.example.android.popularmovies.favorite.constants.FavoritesConstants;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.model.FavoriteModel;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FavoriteHandleAsyncTask extends AsyncTask<FavoriteModel, Void, String> {

    private static final String TAG = FavoriteHandleAsyncTask.class.getName();

    private Context context;
    private ProgressBar favoriteProgressBar;
    private Button favoriteButton;
    private ImageView posterImageView;

    private Bitmap posterBitmap;

    public FavoriteHandleAsyncTask(final Context context,
                                   final ProgressBar favoriteProgressBar,
                                   final Button favoriteButton,
                                   final ImageView posterImageView) {
        Log.d(TAG, "CREATE FavoriteHandleAsyncTask()");
        this.context = context;
        this.favoriteProgressBar = favoriteProgressBar;
        this.favoriteButton = favoriteButton;
        this.posterImageView = posterImageView;
    }

    @Override
    protected void onPreExecute() {
        favoriteButton.setEnabled(false);
        favoriteProgressBar.setVisibility(View.VISIBLE);

        if (posterImageView.getDrawable() instanceof BitmapDrawable) {
            final BitmapDrawable posterBitmapDrawable = (BitmapDrawable) posterImageView.getDrawable();
            this.posterBitmap = posterBitmapDrawable.getBitmap();
        }
    }

    @Override
    protected String doInBackground(FavoriteModel... params) {
        final ContentValues favoriteInsertContentValue = new ContentValues();

        String operationResult = FavoritesConstants.FLAG_FAILURE;
        final FavoriteModel favoriteModel = params[0];
        if (!favoriteModel.isFavorite()) {
            final Uri resultUri = insertFavorite(favoriteInsertContentValue, favoriteModel);
            final boolean result = !resultUri.getLastPathSegment().equals("-1");
            favoriteModel.setFavorite(result);
            if (result) {
                operationResult = FavoritesConstants.FLAG_INSERT_SUCCESS;
            }
        } else {
            final Uri deleteUri = FavoritesContract.FavoritesEntry.CONTENT_MOVIEID_URI.buildUpon()
                    .appendPath(favoriteModel.getMovieListItem().getMovieId()).build();
            final int deleteCount = context.getContentResolver().delete(deleteUri, null, null);
            final boolean result = deleteCount > 0;
            favoriteModel.setFavorite(!result);
            if (result) {
                context.deleteFile("favMovPoster" + favoriteModel.getMovieListItem().getMovieId() + ".png");
                operationResult = FavoritesConstants.FLAG_DELETE_SUCCESS;
            }
        }
        return operationResult;
    }

    private Uri insertFavorite(ContentValues favoriteInsertContentValue, FavoriteModel favoriteModel) {
        Uri resultUri;
        final MovieListItem movieListItem = favoriteModel.getMovieListItem();
        favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_TITLE,
                movieListItem.getTitle());
        favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID,
                movieListItem.getMovieId());
        favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE,
                movieListItem.getReleaseDate());
        favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE,
                movieListItem.getVoteAverage());
        favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW,
                movieListItem.getOverview());

        if (posterBitmap != null) {
            try {
                final String posterFileName = FavoritesConstants.POSTER_FILE_EXTENSION +
                        movieListItem.getMovieId() +
                        FavoritesConstants.POSTER_FILE_EXTENSION;
                final FileOutputStream fileOutputStream = context.openFileOutput(posterFileName, Context.MODE_PRIVATE);
                posterBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

                fileOutputStream.flush();

                fileOutputStream.close();

                favoriteInsertContentValue.put(FavoritesContract.FavoritesEntry.COLUMN_POSTER,
                        posterFileName);

            } catch (final IOException ex) {
                Log.d(TAG, "ERROR ", ex);
            }
        }

        favoriteInsertContentValue.put(FavoritesConstants.VIDEOS_COUNT, 0);
        final List<MovieVideoDescriptor> movieVideoDescriptorList =
                favoriteModel.getMovieVideoDescriptorList();
        if (movieVideoDescriptorList != null && !movieVideoDescriptorList.isEmpty()) {
            favoriteInsertContentValue.put(FavoritesConstants.VIDEOS_COUNT, movieVideoDescriptorList.size());

            for (int i = 0; i < movieVideoDescriptorList.size(); i++) {
                final MovieVideoDescriptor movieVideoDescriptor = movieVideoDescriptorList.get(i);
                favoriteInsertContentValue.put(FavoritesContract.VideosEntry.COLUMN_TITLE + "_" + String.valueOf(i),
                        movieVideoDescriptor.getTitle());
                favoriteInsertContentValue.put(FavoritesContract.VideosEntry.COLUMN_KEY + "_" + String.valueOf(i),
                        movieVideoDescriptor.getKey());
                favoriteInsertContentValue.put(FavoritesContract.VideosEntry.COLUMN_SITE + "_" + String.valueOf(i),
                        movieVideoDescriptor.getSite());
            }

        }

        favoriteInsertContentValue.put(FavoritesConstants.REVIEWS_COUNT, 0);
        final List<MovieReview> movieReviewList = favoriteModel.getMovieReviewList();
        if (movieReviewList != null && !movieReviewList.isEmpty()) {
            favoriteInsertContentValue.put(FavoritesConstants.REVIEWS_COUNT, movieReviewList.size());
            for (int i = 0; i < movieReviewList.size(); i++) {
                final MovieReview movieReview = movieReviewList.get(i);
                favoriteInsertContentValue.put(FavoritesContract.ReviewsEntry.COLUMN_AUTHOR + "_" + String.valueOf(i),
                        movieReview.getAuthor());
                favoriteInsertContentValue.put(FavoritesContract.ReviewsEntry.COLUMN_CONTENT + "_" + String.valueOf(i),
                        movieReview.getContent());
                favoriteInsertContentValue.put(FavoritesContract.ReviewsEntry.COLUMN_URL + "_" + String.valueOf(i),
                        movieReview.getUrl());
            }
        }


        final Uri insertUri = FavoritesContract.FavoritesEntry.CONTENT_URI;
        resultUri = context.getContentResolver().insert(insertUri, favoriteInsertContentValue);
        return resultUri;
    }

    @Override
    protected void onPostExecute(String result) {
        favoriteButton.setEnabled(true);
        favoriteProgressBar.setVisibility(View.INVISIBLE);
        if (FavoritesConstants.FLAG_INSERT_SUCCESS.equals(result)) {
            favoriteButton.setText(context.getText(R.string.detail_favorite_label_off));
        }
        if (FavoritesConstants.FLAG_DELETE_SUCCESS.equals(result)) {
            favoriteButton.setText(context.getText(R.string.detail_favorite_label));
        }
    }
}
