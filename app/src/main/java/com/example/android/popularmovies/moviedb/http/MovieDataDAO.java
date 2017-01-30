package com.example.android.popularmovies.moviedb.http;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.moviedb.constants.MovieDbConstants;
import com.example.android.popularmovies.moviedb.enums.ContentTypeEnum;
import com.example.android.popularmovies.moviedb.enums.OrderTypeEnum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dan.ariton on 26-Jan-17.
 */

public class MovieDataDAO {

    public static String getPopularMovieImgLocation(final ContentTypeEnum contentTypeEnum,
                                                          final OrderTypeEnum orderTypeEnum) {
        String movieJsonList = null;
        List<String> movieUrlList = new ArrayList<>();

        try {
            final Uri popularMoviesUri = Uri.parse(MovieDbConstants.URL_BASE).buildUpon()
                    .appendPath(MovieDbConstants.URL_VERSION_3)
                    .appendPath(contentTypeEnum.getUrlPath())
                    .appendPath(orderTypeEnum.getUrlPath())
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
