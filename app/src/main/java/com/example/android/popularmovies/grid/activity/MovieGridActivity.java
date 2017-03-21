package com.example.android.popularmovies.grid.activity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.favorite.loader.grid.FavoritesGridLoaderCallback;
import com.example.android.popularmovies.grid.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.grid.loader.movielist.MovieDbLoader;
import com.example.android.popularmovies.grid.loader.movielist.MovieDbLoaderCallbacks;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieGridActivity extends AppCompatActivity {

    @BindView(R.id.rv_movie_grid)
    protected RecyclerView movieGridRecyclerView;

    private static final String SELECTED_DATA = "selectedData";
    private static final int DATA_POPULAR = 0;
    private static final int DATA_TOP_RATED = 1;
    private static final int DATA_FAVORITES = 2;

    private int selectedData = DATA_POPULAR;

    private static final String TAG = MovieGridActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);
        ButterKnife.bind(this);

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_DATA)) {
            selectedData = savedInstanceState.getInt(SELECTED_DATA);
        }

        final MovieGridArrayAdapter movieGridArrayAdapter = new MovieGridArrayAdapter(new ArrayList<MovieListItem>());
        movieGridRecyclerView.setHasFixedSize(true);
        movieGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieGridRecyclerView.setAdapter(movieGridArrayAdapter);

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            switch (selectedData) {
                case DATA_POPULAR:
                    startLoadingPopular();
                    break;
                case DATA_TOP_RATED:
                    startLoadingTopRated();
                    break;
                case DATA_FAVORITES:
                    startLoadingFavorites();
                    break;
            }

        } else {
            startLoadingFavorites();
            selectedData = DATA_FAVORITES;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "SAVING selectedData=[" + selectedData + "]");
        outState.putInt(SELECTED_DATA, selectedData);
    }

    private void startLoadingPopular() {

        final Bundle loaderBundle = new Bundle();
        loaderBundle.putString(MovieDbConstants.KEY_CONTENT_TYPE, MovieDbConstants.CONTENT_TYPE_MOVIE);
        loaderBundle.putString(MovieDbConstants.KEY_ORDER_TYPE, MovieDbConstants.ORDER_TYPE_POPULAR);

        if (getSupportLoaderManager().getLoader(MovieDbLoader.LOADER_ID) != null) {
            getSupportLoaderManager().restartLoader(MovieDbLoader.LOADER_ID, loaderBundle,
                    new MovieDbLoaderCallbacks(this, movieGridRecyclerView));
        } else {
            getSupportLoaderManager().initLoader(MovieDbLoader.LOADER_ID, loaderBundle,
                    new MovieDbLoaderCallbacks(this, movieGridRecyclerView));
        }

    }

    private void startLoadingTopRated() {

        final Bundle loaderBundle = new Bundle();
        loaderBundle.putString(MovieDbConstants.KEY_CONTENT_TYPE, MovieDbConstants.CONTENT_TYPE_MOVIE);
        loaderBundle.putString(MovieDbConstants.KEY_ORDER_TYPE, MovieDbConstants.ORDER_TYPE_TOP_RATED);

        if (getSupportLoaderManager().getLoader(MovieDbLoader.LOADER_ID) != null) {
            getSupportLoaderManager().restartLoader(MovieDbLoader.LOADER_ID, loaderBundle,
                    new MovieDbLoaderCallbacks(this, movieGridRecyclerView));
        } else {
            getSupportLoaderManager().initLoader(MovieDbLoader.LOADER_ID, loaderBundle,
                    new MovieDbLoaderCallbacks(this, movieGridRecyclerView));
        }
    }

    private void startLoadingFavorites() {

        if (getSupportLoaderManager().getLoader(FavoritesGridLoaderCallback.LOADER_ID) == null) {
            getSupportLoaderManager().initLoader(FavoritesGridLoaderCallback.LOADER_ID, null,
                    new FavoritesGridLoaderCallback(this, movieGridRecyclerView));
        } else {
            getSupportLoaderManager().restartLoader(FavoritesGridLoaderCallback.LOADER_ID,
                    null, new FavoritesGridLoaderCallback(this, movieGridRecyclerView));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_order_movies, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean optionSelectedResult = super.onOptionsItemSelected(item);

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        switch (item.getItemId()) {
            case R.id.mi_order_popular:
                startLoadingPopular();
                selectedData = DATA_POPULAR;
                optionSelectedResult = true;
                break;
            case R.id.mi_order_top_rated:
                startLoadingTopRated();
                selectedData = DATA_TOP_RATED;
                optionSelectedResult = true;
                break;
            case R.id.mi_favorites:
                startLoadingFavorites();
                selectedData = DATA_FAVORITES;
                optionSelectedResult = true;
                break;
        }

        return optionSelectedResult;
    }
}
