package com.example.android.popularmovies.grid.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dan.ariton on 29-Jan-17.
 */

public class MovieListItem implements Parcelable {

    private String movieId;
    private String title;
    private String releaseDate;
    private String voteAverage;
    private String overview;
    private String posterPath;
    private boolean isFavorite;

    public MovieListItem() {
    }

    // BEGIN PARCELABLE DECLARATION
    protected MovieListItem(Parcel in) {
        movieId = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        isFavorite = Boolean.valueOf(in.readString());
    }

    public static final Creator<MovieListItem> CREATOR = new Creator<MovieListItem>() {
        @Override
        public MovieListItem createFromParcel(Parcel in) {
            return new MovieListItem(in);
        }

        @Override
        public MovieListItem[] newArray(int size) {
            return new MovieListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieId);
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(voteAverage);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
        parcel.writeString(String.valueOf(isFavorite));
    }
    // END PARCELABLE DECLARATION

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "MovieListItem{" +
                "movieId=[" + movieId + ']' +
                ", title=[" + title + ']' +
                ", releaseDate=[" + releaseDate + ']' +
                ", voteAverage=[" + voteAverage + ']' +
                ", overview=[" + overview + ']' +
                ", posterPath=[" + posterPath + ']' +
                ", isFavorite=[" + isFavorite + "]" +
                '}';
    }
}
