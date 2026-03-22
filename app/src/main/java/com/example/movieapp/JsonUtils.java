package com.example.movieapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    if (obj.length() == 0) {
                        Log.w(TAG, "Skipping empty object at index " + i);
                        continue;
                    }

                    String title = obj.isNull("title") ? null : obj.optString("title", null);

                    Integer year = null;
                    if (obj.has("year") && !obj.isNull("year")) {
                        try {
                            year = obj.getInt("year");  
                        } catch (Exception e) {
                            Log.w(TAG, "Could not parse year at index " + i + ": " + e.getMessage());
                            year = null;
                        }
                    }

                    // --- Genre ---
                    String genre = obj.isNull("genre") ? null : obj.optString("genre", null);

                    // --- Poster ---
                    String poster = obj.isNull("poster") ? null : obj.optString("poster", null);

                    if (title == null && year == null && genre == null && poster == null) {
                        Log.w(TAG, "Skipping entry with no usable data at index " + i);
                        continue;
                    }

                    movies.add(new Movie(title, year, genre, poster));

                } catch (Exception e) {
                    Log.e(TAG, "Error parsing movie at index " + i + ": " + e.getMessage());
                }
            }

        } catch (java.io.FileNotFoundException e) {
            Log.e(TAG, "movies.json file not found in assets: " + e.getMessage());
        } catch (org.json.JSONException e) {
            Log.e(TAG, "Invalid JSON format: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error loading movies: " + e.getMessage());
        }

        return movies;
    }
}
