package com.example.android.popularmovies.favorite.db.query;

import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public class FavoritesQuery {

    public static final String CREATE_TABLE =
            "CREATE TABLE " + FavoritesContract.FavoritesEntry.TABLE_NAME + "(\n" +
            "  " + FavoritesContract.FavoritesEntry._ID + " INTEGER PRIMARY KEY, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + " TEXT, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_TITLE + " TEXT, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE + " TEXT, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE + " TEXT, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_OVERVIEW + " TEXT, \n" +
            "  " + FavoritesContract.FavoritesEntry.COLUMN_POSTER + " TEXT \n" +
            ")";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + FavoritesContract.FavoritesEntry.TABLE_NAME;

    public static final String TABLE_EXISTS = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" +
            FavoritesContract.FavoritesEntry.TABLE_NAME + "_backup'";

}
