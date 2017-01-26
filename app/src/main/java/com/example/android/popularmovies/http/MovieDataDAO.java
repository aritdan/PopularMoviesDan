package com.example.android.popularmovies.http;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.constants.MovieDbConstants;

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

    public List<String> getPopularMovieImgLocation() {
        List<String> movieUrlList = new ArrayList<>();

        try {
            final Uri popularMoviesUri = Uri.parse(MovieDbConstants.URL_BASE).buildUpon()
                    .appendPath(MovieDbConstants.URL_VERSION_3)
                    .appendPath(MovieDbConstants.URL_MOVIE)
                    .appendPath(MovieDbConstants.URL_POPULAR)
                    .appendQueryParameter(MovieDbConstants.URL_API_KEY_QUERY, MovieDbConstants.API_KEY_V3)
                    .build();

            final URL moviesUrl = new URL(popularMoviesUri.toString());
            final InputStream moviesInputStream = moviesUrl.openConnection().getInputStream();

            final Scanner scanner = new Scanner(moviesInputStream);
            scanner.useDelimiter("\\A");

            String urlResult = null;
            if (scanner.hasNext()) {
                urlResult = scanner.next();
            }

            final JSONObject movieDb = new JSONObject(urlResult);
            final JSONArray movieJsonArray = movieDb.getJSONArray("results");

            for (int i = 0; i < movieJsonArray.length(); i++) {
                movieUrlList.add(movieJsonArray.getJSONObject(i).getString("poster_path"));
            }


        } catch (IOException | JSONException e) {
            Log.d(MovieDataDAO.class.getName(), "ERROR", e);
        }



        //TODO: replace with movie db api call

        return movieUrlList;
    }

    private List<String> getMockList() {
        List<String> movieUrlList = null;

        //TODO: replace with movie db api call
        movieUrlList = new ArrayList<>();
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");
        movieUrlList.add("1");

        return movieUrlList;
    }

}
