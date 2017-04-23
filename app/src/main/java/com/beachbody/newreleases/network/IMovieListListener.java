package com.beachbody.newreleases.network;

import com.beachbody.newreleases.model.movies.Movie;

import java.util.List;

/**
 * Created by awardak on 4/22/17.
 */

public interface IMovieListListener {
    void onMovieListSuccess(List<Movie> list);
    void onMovieListError(String msg);
}
