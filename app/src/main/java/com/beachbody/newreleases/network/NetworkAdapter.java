package com.beachbody.newreleases.network;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beachbody.newreleases.model.genres.Genre;
import com.beachbody.newreleases.model.genres.Genres;
import com.beachbody.newreleases.model.movies.Movies;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by awardak on 4/22/17.
 *
 * Uses Volley for fetching json content and Glide for fetching images because of the superior
 * memory-saving features of Glide.
 * @see <a href="https://developer.android.com/training/volley/index.html">Volley Doc</a>
 */

public class NetworkAdapter {

    private static final String API_KEY =
            "5ed345211ce499517e6e9fd690ceac7c";
    private static final String MOVIE_LIST_URL =
            "https://api.themoviedb.org/3/movie/now_playing?language=en-US&api_key=" + API_KEY;
    private static final String MOVIE_GENRES_URL =
            "https://api.themoviedb.org/3/genre/movie/list?language=en-US&api_key=" + API_KEY;
    private static final String IMAGES_BASE_URL =
            "http://image.tmdb.org/t/p/w300";

    private static NetworkAdapter mInstance;
    private RequestQueue mRequestQueue;
    Gson mGson = new Gson();

    private NetworkAdapter(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkAdapter getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkAdapter(context);
        }
        return mInstance;
    }

    public void fetchMovies(final IMovieListListener listener) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, MOVIE_LIST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Movies movies = mGson.fromJson(response.toString(), Movies.class);
                        if (listener != null) listener.onMovieListSuccess(movies.movieList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) listener.onMovieListError(error.getMessage());
                    }
                });
        mRequestQueue.add(req);
    }

    public void fetchGenres(final IMovieGenreListener listener) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, MOVIE_GENRES_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Genres genres = mGson.fromJson(response.toString(), Genres.class);
                        if (listener != null) listener.onGenreSuccess(genres.genres);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) listener.onGenreError(error.getMessage());
                    }
                });
        mRequestQueue.add(req);
    }

    public static void fetchImage(Fragment fragment, String filePath, ImageView imageView) {
        Glide.with(fragment)
                .load(IMAGES_BASE_URL + filePath)
                .into(imageView);
    }
}
