package com.example.android.sportsnews.Utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Vaibhav on 8/17/2017.
 */

public final class Network {
    private static final String LOG_TAG = Network.class.getSimpleName();

    public static List<News> fetchNews(String stringURL){

        URL requestURL = createURL(stringURL);

        String jsonResponse = "";

        try {
            jsonResponse = makeHttpRequest(requestURL);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private static URL createURL(String stringURL) {
        URL url = null;

        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Unable to create URL with " + stringURL, e);
        }

        return url;
    }

    private static String makeHttpRequest(URL requestURL) throws IOException {
        String jsonResponse = "";

        // escape early if URL is null
        if (requestURL == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        return null;
    }

}
