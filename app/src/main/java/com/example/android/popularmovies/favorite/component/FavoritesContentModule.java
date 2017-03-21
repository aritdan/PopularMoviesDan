package com.example.android.popularmovies.favorite.component;

import android.content.Context;
import android.content.UriMatcher;

import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dan.ariton on 19-Mar-17.
 */

@Module
public class FavoritesContentModule {

    private Context context;

    public FavoritesContentModule(final Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

    @Provides
    public UriMatcher getFavoritesMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES,
                FavoritesContract.MATCHER_ID_FAVORITES_ALL);
        matcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES + "/#",
                FavoritesContract.MATCHER_ID_FAVORITES_ID);
        matcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES + "/" +
                FavoritesContract.PATH_MOVIE_SELECTOR + "/#",
                FavoritesContract.MATCHER_ID_FAVORITES_MOVIEID);
        matcher.addURI(FavoritesContract.AUTHORITY, FavoritesContract.PATH_FAVORITES + "/" +
                FavoritesContract.PATH_MOVIE_SELECTOR + "/" + FavoritesContract.PATH_COUNT + "/#",
                FavoritesContract.MATCHER_ID_FAVORITES_COUNT_MOVIEID);

        matcher.addURI(FavoritesContract.AUTHORITY,
                FavoritesContract.PATH_VIDEOS, FavoritesContract.MATCHER_ID_VIDEOS);
        matcher.addURI(FavoritesContract.AUTHORITY,
                FavoritesContract.PATH_VIDEOS + "/" + FavoritesContract.PATH_MOVIE_SELECTOR + "/#",
                FavoritesContract.MATCHER_ID_VIDEOS_MOVIEID);

        matcher.addURI(FavoritesContract.AUTHORITY,
                FavoritesContract.PATH_REVIEWS, FavoritesContract.MATCHER_ID_REVIEWS);
        matcher.addURI(FavoritesContract.AUTHORITY,
                FavoritesContract.PATH_REVIEWS + "/" + FavoritesContract.PATH_MOVIE_SELECTOR + "/#",
                FavoritesContract.MATCHER_ID_REVIEWS_MOVIEID);

        return matcher;
    }

}
