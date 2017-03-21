package com.example.android.popularmovies.favorite.component;

import com.example.android.popularmovies.favorite.contentprovider.FavoritesContentProvider;

import dagger.Component;

/**
 * Created by dan.ariton on 19-Mar-17.
 */

@FavoritesScope
@Component(modules = {FavoritesContentModule.class})
public interface FavoritesComponent {

    public void inject(final FavoritesContentProvider favoritesContentProvider);

}
