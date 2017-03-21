package com.example.android.popularmovies.favorite.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.popularmovies.favorite.component.DaggerFavoritesComponent;
import com.example.android.popularmovies.favorite.component.FavoritesContentModule;
import com.example.android.popularmovies.favorite.constants.FavoritesConstants;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.db.dao.FavoritesDAO;
import com.example.android.popularmovies.favorite.db.dao.ReviewsDAO;
import com.example.android.popularmovies.favorite.db.dao.VideosDAO;
import com.example.android.popularmovies.favorite.db.helper.FavoritesDbHelper;
import com.example.android.popularmovies.favorite.factory.FavoritesContentValuesFactory;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 19-Mar-17.
 */

public class FavoritesContentProvider extends ContentProvider {

    private static final String TAG = FavoritesContentProvider.class.getName();

    private UriMatcher uriMatcher;
    private FavoritesDbHelper favoritesDbHelper;

    private FavoritesDAO favoritesDAO;
    private VideosDAO videosDAO;
    private ReviewsDAO reviewsDAO;

    private FavoritesContentValuesFactory favoritesContentValuesFactory;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "ENTER onCreate()");
        DaggerFavoritesComponent.builder()
                .favoritesContentModule(new FavoritesContentModule(this.getContext()))
                .build().inject(this);
        Log.d(TAG, "EXIT onCreate()");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "ENTER query() uri=[" + uri + "], projection=[" + projection + "], selection=[" + selection + "], " +
                "selectionArgs=[" + selectionArgs + "], sortOrder=[" + sortOrder + "]");

        final int match = uriMatcher.match(uri);

        Cursor resultCursor = null;
        switch (match) {
            case FavoritesContract.MATCHER_ID_FAVORITES_ALL:
                resultCursor = favoritesDAO.getAllMovies();
                break;
            case FavoritesContract.MATCHER_ID_FAVORITES_COUNT_MOVIEID:
                final String movieId = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
                final Boolean favoriteExists = favoritesDAO.getMovieExists(movieId);
                resultCursor = new MatrixCursor(new String[] {FavoritesConstants.FAVORITE_EXISTS});
                ((MatrixCursor) resultCursor).addRow(new Object[] {String.valueOf(favoriteExists)});
                break;
            case FavoritesContract.MATCHER_ID_REVIEWS_MOVIEID:
                final String movieIdReview = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
                final Cursor favoriteReviewCursor = favoritesDAO.getMovieByMovieId(movieIdReview);
                favoriteReviewCursor.moveToFirst();
                final String favoriteIdReview = favoriteReviewCursor.getString(favoriteReviewCursor.getColumnIndex(FavoritesContract.FavoritesEntry._ID));
                resultCursor = reviewsDAO.getReviewsByMovieId(favoriteIdReview);
                break;
            case FavoritesContract.MATCHER_ID_VIDEOS_MOVIEID:
                final String movieIdVideo = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
                final Cursor favoriteVideoCursor = favoritesDAO.getMovieByMovieId(movieIdVideo);
                favoriteVideoCursor.moveToFirst();
                final String favoriteIdVideo = favoriteVideoCursor.getString(favoriteVideoCursor.getColumnIndex(FavoritesContract.FavoritesEntry._ID));
                resultCursor = videosDAO.getVideosByMovieId(favoriteIdVideo);
                break;
            default:
                throw new UnsupportedOperationException("NO MATCH FOUND FOR URI=[" + uri + "]");
        }

        Log.d(TAG, "EXIT query()");
        return resultCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "ENTER insert() uri=[" + uri + "], values=[" + values + "]");
        final int match = uriMatcher.match(uri);
        Uri insertUri = null;

        switch (match) {
            case FavoritesContract.MATCHER_ID_FAVORITES_ALL:
                final SQLiteDatabase db = favoritesDbHelper.getWritableDatabase();
                try {
                    db.beginTransaction();

                    final long favoriteId = favoritesDAO.insertNewFavorite(
                            favoritesContentValuesFactory.getFavoritesContentValues(values));

                    insertUri = ContentUris.withAppendedId(FavoritesContract.FavoritesEntry.CONTENT_URI,
                            favoriteId);

                    final int videosSize = values.getAsInteger(FavoritesConstants.VIDEOS_COUNT);
                    for (int i = 0; i < videosSize; i++) {
                        videosDAO.insertNewVideo(favoritesContentValuesFactory.getVideoContentValues(values,
                                favoriteId, i));
                    }

                    final int reviewsSize = values.getAsInteger(FavoritesConstants.REVIEWS_COUNT);
                    for (int i = 0; i < reviewsSize; i++) {
                        reviewsDAO.insertNewReview(favoritesContentValuesFactory.getReviewContentValues(values,
                                favoriteId, i));
                    }

                    db.setTransactionSuccessful();
                } catch (final Exception ex) {
                    Log.d(TAG, "ERROR ", ex);
                } finally {
                    db.endTransaction();
                }
                break;
            default:
                throw new UnsupportedOperationException("NO MATCH FOUND FOR URI=[" + uri + "]");

        }

        Log.d(TAG, "EXIT insert() insertUri=[" + insertUri + "]");
        return insertUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "ENTER delete() uri=[" + uri + "], selection=[" + selection + "], " +
                "selectionArgs=[" + selectionArgs + "]");

        final int match = uriMatcher.match(uri);
        Log.d(TAG, "delete(): MATCHED URI WITH " + match);

        int deleteCount = 0;

        switch (match) {
            case FavoritesContract.MATCHER_ID_FAVORITES_MOVIEID:
                final String movieId = uri.getPathSegments().get(uri.getPathSegments().size() - 1);
                try {
                    favoritesDbHelper.getWritableDatabase().beginTransaction();

                    final Cursor favoriteRow = favoritesDAO.getMovieByMovieId(movieId);
                    if (favoriteRow != null && favoriteRow.moveToFirst()) {
                        final String favoriteId = favoriteRow.getString(favoriteRow
                                .getColumnIndex(FavoritesContract.FavoritesEntry._ID));

                        videosDAO.deleteVideoByMovieId(favoriteId);
                        reviewsDAO.deleteReviewByMovieId(favoriteId);
                        deleteCount = favoritesDAO.deleteFavorite(favoriteId);
                    }
                    favoritesDbHelper.getWritableDatabase().setTransactionSuccessful();
                } catch (final Exception ex) {
                    Log.d(TAG, "ERROR ", ex);
                } finally {
                    favoritesDbHelper.getWritableDatabase().endTransaction();
                }
                break;
            default:
                throw new UnsupportedOperationException("NO MATCH FOUND FOR URI=[" + uri + "]");
        }

        Log.d(TAG, "EXIT delete() deleteCount=[" + deleteCount + "]");
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Inject
    public void setUriMatcher(UriMatcher uriMatcher) {
        this.uriMatcher = uriMatcher;
    }

    @Inject
    public void setFavoritesDAO(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    @Inject
    public void setVideosDAO(VideosDAO videosDAO) {
        this.videosDAO = videosDAO;
    }

    @Inject
    public void setReviewsDAO(ReviewsDAO reviewsDAO) {
        this.reviewsDAO = reviewsDAO;
    }

    @Inject
    public void setFavoritesDbHelper(FavoritesDbHelper favoritesDbHelper) {
        this.favoritesDbHelper = favoritesDbHelper;
    }

    @Inject
    public void setFavoritesContentValuesFactory(FavoritesContentValuesFactory favoritesContentValuesFactory) {
        this.favoritesContentValuesFactory = favoritesContentValuesFactory;
    }
}
