package com.beachbody.newreleases.list;

import android.content.Context;

import com.beachbody.newreleases.model.movies.Movie;

import java.util.List;
import java.util.Map;

/**
 * Created by awardak on 4/22/17.
 */

public interface IMovieListContract {

    interface IView {
        void onMoviesSuccess(List<Movie> movies);
        void onMoviesError(String msg);
        void onGenresSuccess(Map<Integer, String> genres);
        void onGenresError(String msg);
        Context getAppContext();
    }

    interface IPresenter {
        void loadMovies();
        void loadGenres();
    }
}
