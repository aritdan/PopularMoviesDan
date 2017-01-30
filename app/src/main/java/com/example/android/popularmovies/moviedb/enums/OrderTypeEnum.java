package com.example.android.popularmovies.moviedb.enums;

/**
 * Created by dan.ariton on 27-Jan-17.
 */

public enum OrderTypeEnum {

    POPULAR("popular"),
    TOP_RATED("top_rated");

    private String urlPath;

    private OrderTypeEnum(final String urlPath) {
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
