package com.example.android.popularmovies.favorite.db.contract;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public class FavoritesContract  {

    public static final String AUTHORITY = "com.example.android.popularmovies";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_FAVORITES = "favorites";

    public static final String PATH_VIDEOS = "videos";
    public static final String PATH_REVIEWS = "reviews";
    public static final String PATH_MOVIE_SELECTOR = "movie";
    public static final String PATH_COUNT = "count";

    public static final int MATCHER_ID_FAVORITES_ALL = 101;
    public static final int MATCHER_ID_FAVORITES_ID = 102;
    public static final int MATCHER_ID_FAVORITES_MOVIEID = 103;
    public static final int MATCHER_ID_FAVORITES_COUNT_MOVIEID = 104;

    public static final int MATCHER_ID_VIDEOS = 201;
    public static final int MATCHER_ID_VIDEOS_MOVIEID = 202;

    public static final int MATCHER_ID_REVIEWS = 301;
    public static final int MATCHER_ID_REVIEWS_MOVIEID = 302;

    private FavoritesContract() {}

    public static class FavoritesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_FAVORITES)
                .build();

        public static final Uri CONTENT_MOVIEID_URI = CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE_SELECTOR).build();

        public static final Uri CONTENT_COUNT_MOVIEID_URI = CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE_SELECTOR).appendPath(PATH_COUNT).build();

        public static final String TABLE_NAME = "pop_mov_favorites";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER = "poster";

    }

    public static class VideosEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_VIDEOS)
                .build();

        public static final Uri MOVIE_CONTENT_URI = CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE_SELECTOR).build();

        public static final String TABLE_NAME = "pop_mov_videos";

        public static final String COLUMN_MOVIE = "movie";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_SITE = "site";

    }

    public static class ReviewsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_REVIEWS)
                .build();

        public static final Uri MOVIE_CONTENT_URI = CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE_SELECTOR).build();

        public static final String TABLE_NAME = "pop_mov_reviews";

        public static final String COLUMN_MOVIE = "movie";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_URL = "url";

    }

}
