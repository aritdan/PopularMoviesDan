package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.moviedb.pojo.MovieListItem;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

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

    @BindString(R.string.detail_synopsis)
    protected String synopsisHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final MovieListItem movieListItem = intent.getParcelableExtra(MovieDbConstants.MOVIE_ITEM);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/" + movieListItem.getPosterUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterImageView);

        titleTextView.setText(movieListItem.getTitle());
        releaseDateTextView.setText(movieListItem.getReleaseDate());
        voteAvgTextView.setText(movieListItem.getVoteAverage());
        synopsisTextView.setText(synopsisHeader + movieListItem.getOverview());

    }
}
