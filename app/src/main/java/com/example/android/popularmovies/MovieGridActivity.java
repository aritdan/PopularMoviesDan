package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.android.popularmovies.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;
import com.example.android.popularmovies.task.MovieListTask;

import java.util.ArrayList;

public class MovieGridActivity extends AppCompatActivity {

    private RecyclerView movieGridRecyclerView;
    private ProgressBar movieLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        movieGridRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_grid);
        movieLoadProgressBar = (ProgressBar) findViewById(R.id.pb_movie_load);

        final MovieGridArrayAdapter movieGridArrayAdapter = new MovieGridArrayAdapter(new ArrayList<MovieListItem>());
        movieGridRecyclerView.setHasFixedSize(true);
        movieGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        movieGridRecyclerView.setAdapter(movieGridArrayAdapter);
        new MovieListTask(movieGridRecyclerView, MovieDbConstants.CONTENT_TYPE_MOVIE, MovieDbConstants.ORDER_TYPE_POPULAR).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_order_movies, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean optionSelectedResult = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.mi_order_popular) {
            new MovieListTask(movieGridRecyclerView, MovieDbConstants.CONTENT_TYPE_MOVIE, MovieDbConstants.ORDER_TYPE_POPULAR).execute();
            optionSelectedResult = true;
        } else if (item.getItemId() == R.id.mi_order_top_rated) {
            new MovieListTask(movieGridRecyclerView, MovieDbConstants.CONTENT_TYPE_MOVIE, MovieDbConstants.ORDER_TYPE_TOP_RATED).execute();
            optionSelectedResult = true;
        }

        return optionSelectedResult;
    }
}
