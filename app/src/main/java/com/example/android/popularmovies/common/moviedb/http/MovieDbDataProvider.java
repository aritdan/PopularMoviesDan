package com.example.android.popularmovies.common.moviedb.http;

import android.util.Log;

import com.example.android.popularmovies.common.component.LoaderScope;
import com.example.android.popularmovies.common.moviedb.constants.MovieDbConstants;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

@LoaderScope
public class MovieDbDataProvider {

    private static final String TAG = MovieDbDataProvider.class.getName();

    private OkHttpClient okHttpClient;

    @Inject
    public MovieDbDataProvider(final OkHttpClient okHttpClient) {
        Log.d(TAG, "CREATE MovieDbDataProvider");
        this.okHttpClient = okHttpClient;
    }

    public String getMovieList(final String contentType,
                                      final String orderType) {
        Log.d(TAG, "ENTER getMovieList() contentType=[" + contentType + "], orderType=[" + orderType + "]");

        final HttpUrl moviesUrl = new HttpUrl.Builder().scheme(MovieDbConstants.URL_SCHEME)
                .host(MovieDbConstants.URL_HOST)
                .addPathSegment(MovieDbConstants.URL_VERSION_3)
                .addPathSegment(contentType)
                .addPathSegment(orderType)
                .addQueryParameter(MovieDbConstants.URL_API_KEY_QUERY, MovieDbConstants.API_KEY_V3)
                .build();


        final String movieListJson = executeHttpGetAndReturnBody(moviesUrl);

        Log.d(TAG, "EXIT getMovieList() movieListJson=[" + movieListJson + "]");
        return movieListJson;
    }

    public String getMovieVideoList(final String movieId) {
        Log.d(TAG, "ENTER getMovieVideoList() movieId=[" + movieId + "]");

        final HttpUrl videosUrl = new HttpUrl.Builder().scheme(MovieDbConstants.URL_SCHEME)
                .host(MovieDbConstants.URL_HOST)
                .addPathSegment(MovieDbConstants.URL_VERSION_3)
                .addPathSegment(MovieDbConstants.CONTENT_TYPE_MOVIE)
                .addPathSegment(movieId)
                .addPathSegment(MovieDbConstants.KEY_VIDEOS)
                .addQueryParameter(MovieDbConstants.URL_API_KEY_QUERY, MovieDbConstants.API_KEY_V3)
                .build();


        final String movieVideoListJson = executeHttpGetAndReturnBody(videosUrl);

        Log.d(TAG, "EXIT getMovieVideoList() movieVideoListJson=[" + movieVideoListJson + "]");
        return movieVideoListJson;
    }

    public String getMovieReviewList(final String movieId) {
        Log.d(TAG, "ENTER getMovieReviewList() movieId=[" + movieId + "]");

        final HttpUrl videosUrl = new HttpUrl.Builder().scheme(MovieDbConstants.URL_SCHEME)
                .host(MovieDbConstants.URL_HOST)
                .addPathSegment(MovieDbConstants.URL_VERSION_3)
                .addPathSegment(MovieDbConstants.CONTENT_TYPE_MOVIE)
                .addPathSegment(movieId)
                .addPathSegment(MovieDbConstants.KEY_REVIEWS)
                .addQueryParameter(MovieDbConstants.URL_API_KEY_QUERY, MovieDbConstants.API_KEY_V3)
                .build();


        final String movieReviewListJson = executeHttpGetAndReturnBody(videosUrl);

        Log.d(TAG, "EXIT getMovieReviewList() movieReviewListJson=[" + movieReviewListJson + "]");
        return movieReviewListJson;
    }

    private String executeHttpGetAndReturnBody(final HttpUrl httpUrl) {
        Log.d(TAG, "ENTER executeHttpGetAndReturnBody() httpUrl=[" + String.valueOf(httpUrl) + "]");
        String responseBody = null;

        try {
            final Request request = new Request.Builder().url(httpUrl)
                    .get()
                    .build();

            final Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();

        } catch (final IOException ex) {
            Log.d(TAG, "ERROR " + ex.getMessage(), ex);
        }

        Log.d(TAG, "ENTER executeHttpGetAndReturnBody() responseBody=[" + responseBody + "]");
        return responseBody;
    }

}
