package com.example.android.popularmovies.favorite.db.query;

import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public class VideosQuery {

    public static final String CREATE_TABLE =
            "CREATE TABLE " + FavoritesContract.VideosEntry.TABLE_NAME + "(\n" +
                    "  " + FavoritesContract.VideosEntry._ID + " INTEGER PRIMARY KEY, \n" +
                    "  " + FavoritesContract.VideosEntry.COLUMN_MOVIE + " INTEGER, \n" +
                    "  " + FavoritesContract.VideosEntry.COLUMN_TITLE + " TEXT, \n" +
                    "  " + FavoritesContract.VideosEntry.COLUMN_KEY + " TEXT, \n" +
                    "  " + FavoritesContract.VideosEntry.COLUMN_SITE + " TEXT, \n" +
                    "  FOREIGN KEY (" + FavoritesContract.VideosEntry.COLUMN_MOVIE + ") REFERENCES "
                    + FavoritesContract.FavoritesEntry.TABLE_NAME + " (" + FavoritesContract.FavoritesEntry._ID + ") \n" +
                    ")";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + FavoritesContract.VideosEntry.TABLE_NAME;

    public static final String TABLE_EXISTS = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" +
            FavoritesContract.VideosEntry.TABLE_NAME + "_backup'";

}
