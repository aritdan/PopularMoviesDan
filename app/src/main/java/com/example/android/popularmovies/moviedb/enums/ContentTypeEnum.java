package com.example.android.popularmovies.moviedb.enums;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

public enum ContentTypeEnum {

    MOVIE("movie");

    private String urlPath;

    private ContentTypeEnum(final String urlPath) {
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        return urlPath;
    }

}
