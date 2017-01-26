package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.android.popularmovies.adapter.MovieGridArrayAdapter;
import com.example.android.popularmovies.task.MovieListTask;

import java.util.ArrayList;

public class MovieGrid extends AppCompatActivity {

    private RecyclerView movieGridRecyclerView;
    private ProgressBar movieLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        movieGridRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_grid);
        movieLoadProgressBar = (ProgressBar) findViewById(R.id.pb_movie_load);

        final MovieGridArrayAdapter movieGridArrayAdapter = new MovieGridArrayAdapter(new ArrayList<String>());
        movieGridRecyclerView.setHasFixedSize(true);
        movieGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        movieGridRecyclerView.setAdapter(movieGridArrayAdapter);
        new MovieListTask(movieGridRecyclerView).execute();

    }


}
