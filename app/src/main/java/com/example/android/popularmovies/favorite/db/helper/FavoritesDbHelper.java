package com.example.android.popularmovies.favorite.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.popularmovies.favorite.component.FavoritesScope;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.db.query.FavoritesQuery;
import com.example.android.popularmovies.favorite.db.query.ReviewsQuery;
import com.example.android.popularmovies.favorite.db.query.VideosQuery;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 18-Mar-17.
 */

@FavoritesScope
public class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String TAG = FavoritesDbHelper.class.getName();

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "favorites.db";

    @Inject
    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "ENTER onCreate()");
        Log.d(TAG, "CREATE " + FavoritesContract.FavoritesEntry.TABLE_NAME);
        db.execSQL(FavoritesQuery.CREATE_TABLE);
        Log.d(TAG, "CREATE " + FavoritesContract.VideosEntry.TABLE_NAME);
        db.execSQL(VideosQuery.CREATE_TABLE);
        Log.d(TAG, "CREATE " + FavoritesContract.ReviewsEntry.TABLE_NAME);
        db.execSQL(ReviewsQuery.CREATE_TABLE);
        Log.d(TAG, "EXIT onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "ENTER onUpgrade() oldVersion=[" + oldVersion + "], newVersion=[" + newVersion +
                "]");

        Log.d(TAG, "DROP " + FavoritesContract.ReviewsEntry.TABLE_NAME);
        db.execSQL(ReviewsQuery.DROP_TABLE);

        Log.d(TAG, "DROP " + FavoritesContract.VideosEntry.TABLE_NAME);
        db.execSQL(VideosQuery.DROP_TABLE);

        Log.d(TAG, "DROP " + FavoritesContract.FavoritesEntry.TABLE_NAME);
        db.execSQL(FavoritesQuery.DROP_TABLE);
        onCreate(db);
        Log.d(TAG, "EXIT onUpgrade()");
    }


}
