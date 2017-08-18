package com.example.android.sportsnews.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * This {@link Network} class makes http request using the provided url and parses the JSON response
 * and returns a list of type {@link List<News>}
 */

public final class Network {
    private static final String LOG_TAG = Network.class.getSimpleName();

    public static List<News> fetchNews(String stringURL) {

        URL requestURL = createURL(stringURL);

        String jsonResponse = "";

        try {
            jsonResponse = makeHttpRequest(requestURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractNews(jsonResponse);
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

        try {
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int statusCode = urlConnection.getResponseCode();

            // if response code is 200 then get input stream
            if (statusCode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + statusCode);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream,
                Charset.forName("UTF-8")
        );

        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = reader.readLine();

        while (line != null) {
            output.append(line);
            line = reader.readLine();
        }

        return output.toString();
    }

    private static List<News> extractNews(String jsonResponse) {
        List<News> newsList = new ArrayList<>();

        try {
            JSONObject main = new JSONObject(jsonResponse);

            // get response JSONObject
            JSONObject response = main.getJSONObject("response");

            // get results JSONArray
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject object = results.getJSONObject(i);

                String title = "N/A", section = "N/A", url = null;

                if (object.has("webTitle")) {
                    title = object.getString("webTitle");
                }

                if (object.has("sectionName")) {
                    section = object.getString("sectionName");
                }

                if (object.has("webUrl")) {
                    url = object.getString("webUrl");
                }

                newsList.add(new News(title, section, url));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse the JSON response: " + jsonResponse, e);
        }

        return newsList;
    }

}
