package com.example.android.popularmovies.favorite.db.query;

import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

public class ReviewsQuery {

    public static final String CREATE_TABLE =
            "CREATE TABLE " + FavoritesContract.ReviewsEntry.TABLE_NAME + "(\n" +
                    "  " + FavoritesContract.ReviewsEntry._ID + " INTEGER PRIMARY KEY, \n" +
                    "  " + FavoritesContract.ReviewsEntry.COLUMN_MOVIE + " INTEGER, \n" +
                    "  " + FavoritesContract.ReviewsEntry.COLUMN_AUTHOR + " TEXT, \n" +
                    "  " + FavoritesContract.ReviewsEntry.COLUMN_CONTENT + " TEXT, \n" +
                    "  " + FavoritesContract.ReviewsEntry.COLUMN_URL + " TEXT, \n" +
                    "  FOREIGN KEY (" + FavoritesContract.ReviewsEntry.COLUMN_MOVIE + ") REFERENCES "
                    + FavoritesContract.FavoritesEntry.TABLE_NAME + " (" + FavoritesContract.FavoritesEntry._ID + ") \n" +
                    ")";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + FavoritesContract.ReviewsEntry.TABLE_NAME;

    public static final String TABLE_EXISTS = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" +
            FavoritesContract.ReviewsEntry.TABLE_NAME + "_backup'";

}
