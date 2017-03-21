package com.example.android.popularmovies.favorite.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.android.popularmovies.favorite.component.FavoritesScope;
import com.example.android.popularmovies.favorite.db.helper.FavoritesDbHelper;

import javax.inject.Inject;

/**
 * Created by dan.ariton on 21-Mar-17.
 */

@FavoritesScope
public abstract class AbstractDAO {

    protected FavoritesDbHelper favoritesDbHelper;

    protected SQLiteDatabase getDbForUse(final SQLiteDatabase transactionDb) {
        SQLiteDatabase usedDb = transactionDb;
        if (usedDb == null) {
            usedDb = favoritesDbHelper.getWritableDatabase();
        }
        return usedDb;
    }


}
