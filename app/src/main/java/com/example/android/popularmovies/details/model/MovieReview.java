package com.example.android.popularmovies.details.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dan.ariton on 10-Mar-17.
 */

public class MovieReview implements Parcelable {

    private String author;
    private String content;
    private String url;

    public MovieReview() {}

    protected MovieReview(final Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "author=[" + author + ']' +
                ", content=[" + content + ']' +
                ", url=[" + url + ']' +
                '}';
    }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel source) {
            return new MovieReview(source);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }
}
