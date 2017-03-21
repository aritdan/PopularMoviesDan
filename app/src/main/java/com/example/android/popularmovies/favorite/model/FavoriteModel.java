package com.example.android.popularmovies.favorite.model;

import android.graphics.Bitmap;

import com.example.android.popularmovies.details.model.MovieReview;
import com.example.android.popularmovies.details.model.MovieVideoDescriptor;
import com.example.android.popularmovies.grid.model.MovieListItem;

import java.util.List;

/**
 * Created by dan.ariton on 20-Mar-17.
 */

public class FavoriteModel {

    private MovieListItem movieListItem;
    private List<MovieVideoDescriptor> movieVideoDescriptorList;
    private List<MovieReview> movieReviewList;
    private boolean favorite = true;

    public MovieListItem getMovieListItem() {
        return movieListItem;
    }

    public void setMovieListItem(MovieListItem movieListItem) {
        this.movieListItem = movieListItem;
    }

    public List<MovieVideoDescriptor> getMovieVideoDescriptorList() {
        return movieVideoDescriptorList;
    }

    public void setMovieVideoDescriptorList(List<MovieVideoDescriptor> movieVideoDescriptorList) {
        this.movieVideoDescriptorList = movieVideoDescriptorList;
    }

    public List<MovieReview> getMovieReviewList() {
        return movieReviewList;
    }

    public void setMovieReviewList(List<MovieReview> movieReviewList) {
        this.movieReviewList = movieReviewList;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
