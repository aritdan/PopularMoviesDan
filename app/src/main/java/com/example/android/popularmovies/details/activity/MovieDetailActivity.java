package com.example.android.popularmovies.details.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.details.loader.reviews.MovieDbReviewLoader;
import com.example.android.popularmovies.details.loader.reviews.MovieDbReviewLoaderCallbacks;
import com.example.android.popularmovies.details.loader.videos.MovieDbVideosLoader;
import com.example.android.popularmovies.details.loader.videos.MovieDbVideosLoaderCallbacks;
import com.example.android.popularmovies.favorite.constants.FavoritesConstants;
import com.example.android.popularmovies.favorite.db.contract.FavoritesContract;
import com.example.android.popularmovies.favorite.loader.detail.MovieExistsLoaderCallbacks;
import com.example.android.popularmovies.favorite.loader.review.FavoriteReviewLoaderCallbacks;
import com.example.android.popularmovies.favorite.loader.video.FavoriteVideosLoaderCallbacks;
import com.example.android.popularmovies.favorite.model.FavoriteModel;
import com.example.android.popularmovies.favorite.task.FavoriteHandleAsyncTask;
import com.example.android.popularmovies.grid.model.MovieListItem;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MovieDetailActivity.class.getName();
    private FavoriteModel favoriteModel;

    @BindView(R.id.iv_detail_poster)
    protected ImageView posterImageView;

    @BindView(R.id.tv_detail_title)
    protected TextView titleTextView;

    @BindView(R.id.tv_detail_release_date)
    protected TextView releaseDateTextView;

    @BindView(R.id.tv_detail_vote_avg)
    protected TextView voteAvgTextView;

    @BindView(R.id.tv_detail_synopsis)
    protected TextView synopsisTextView;

    @BindView(R.id.videos_container)
    protected LinearLayout videosContainerView;

    @BindView(R.id.reviews_container)
    protected LinearLayout reviewsContainerView;

    @BindView(R.id.btn_detail_favorite)
    protected Button favoriteButton;

    @BindView(R.id.pb_favorite_load)
    protected ProgressBar favoriteLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ENTER onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final MovieListItem movieListItem = intent.getParcelableExtra(MovieDbConstants.MOVIE_ITEM);
        favoriteModel = new FavoriteModel();
        favoriteModel.setMovieListItem(movieListItem);
        favoriteLoadProgressBar.bringToFront();

        if (movieListItem.isFavorite()) {
            Picasso.with(this)
                    .load(new File(movieListItem.getPosterPath()))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(posterImageView);
        } else {
            Picasso.with(this)
                    .load("http://image.tmdb.org/t/p/w500/" + movieListItem.getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(posterImageView);
        }
        titleTextView.setText(movieListItem.getTitle());
        releaseDateTextView.setText(movieListItem.getReleaseDate());
        voteAvgTextView.setText(movieListItem.getVoteAverage());
        synopsisTextView.setText(movieListItem.getOverview());
        favoriteButton.setOnClickListener(this);

        final Bundle movieIdBundle = new Bundle();
        movieIdBundle.putString(MovieDbConstants.KEY_MOVIE_ID, movieListItem.getMovieId());

        if (movieListItem.isFavorite()) {
            getSupportLoaderManager().initLoader(FavoriteReviewLoaderCallbacks.LOADER_ID,
                    movieIdBundle,
                    new FavoriteReviewLoaderCallbacks(this, reviewsContainerView));
            getSupportLoaderManager().initLoader(FavoriteVideosLoaderCallbacks.LOADER_ID,
                    movieIdBundle,
                    new FavoriteVideosLoaderCallbacks(this, videosContainerView));
        } else {
            getSupportLoaderManager().initLoader(MovieDbVideosLoader.LOADER_ID, movieIdBundle,
                    new MovieDbVideosLoaderCallbacks(this, videosContainerView, favoriteModel));
            getSupportLoaderManager().initLoader(MovieDbReviewLoader.LOADER_ID, movieIdBundle,
                    new MovieDbReviewLoaderCallbacks(this, reviewsContainerView, favoriteModel));
        }

        final Bundle favoritesBundle = new Bundle();
        favoritesBundle.putString(FavoritesConstants.PARAM_MOVIEID, movieListItem.getMovieId());
        getSupportLoaderManager().initLoader(MovieExistsLoaderCallbacks.LOADER_ID, favoritesBundle,
                new MovieExistsLoaderCallbacks(this, favoriteLoadProgressBar, favoriteButton, favoriteModel));

        Log.d(TAG, "EXIT onCreate()");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "ENTER onClick()");
        new FavoriteHandleAsyncTask(this, favoriteLoadProgressBar, favoriteButton,
                posterImageView).execute(favoriteModel);
        Log.d(TAG, "EXIT onClick()");
    }
}
