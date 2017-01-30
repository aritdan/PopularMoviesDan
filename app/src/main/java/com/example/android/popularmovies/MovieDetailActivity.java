package com.example.android.popularmovies;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView voteAvgTextView;
    private TextView synopsisTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final Intent intent = getIntent();

        posterImageView = (ImageView) findViewById(R.id.iv_detail_poster);
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/" + intent.getStringExtra(MovieDbConstants.MOVIE_POSTER_PATH))
                .into(posterImageView);

        titleTextView = (TextView) findViewById(R.id.tv_detail_title);
        titleTextView.setText(intent.getStringExtra(MovieDbConstants.MOVIE_ORIGINAL_TITLE));

        releaseDateTextView = (TextView) findViewById(R.id.tv_detail_release_date);
        releaseDateTextView.setText(intent.getStringExtra(MovieDbConstants.MOVIE_RELEASE_DATE));

        voteAvgTextView = (TextView) findViewById(R.id.tv_detail_vote_avg);
        voteAvgTextView.setText(intent.getStringExtra(MovieDbConstants.MOVIE_VOTE_AVERAGE));

        synopsisTextView = (TextView) findViewById(R.id.tv_detail_synopsis);
        synopsisTextView.setText(intent.getStringExtra(MovieDbConstants.MOVIE_OVERVIEW));

    }
}
