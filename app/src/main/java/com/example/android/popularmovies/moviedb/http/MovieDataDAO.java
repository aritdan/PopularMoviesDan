package com.example.android.popularmovies.moviedb.http;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieDataDAO {

    public static String getMovieList(final String contentType,
                                      final String orderType) {
        String movieJsonList = null;

        try {
            final Uri popularMoviesUri = Uri.parse(MovieDbConstants.URL_BASE).buildUpon()
                    .appendPath(MovieDbConstants.URL_VERSION_3)
                    .appendPath(contentType)
                    .appendPath(orderType)
                    .appendQueryParameter(MovieDbConstants.URL_API_KEY_QUERY, MovieDbConstants.API_KEY_V3)
                    .build();

            final URL moviesUrl = new URL(popularMoviesUri.toString());
            final InputStream moviesInputStream = moviesUrl.openConnection().getInputStream();

            final Scanner scanner = new Scanner(moviesInputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                movieJsonList = scanner.next();
            }


        } catch (IOException e) {
            Log.d(MovieDataDAO.class.getName(), "ERROR", e);
        }

        return movieJsonList;
    }

}
