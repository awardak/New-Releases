package com.beachbody.newreleases.network;

import com.beachbody.newreleases.model.genres.Genre;

import java.util.List;

/**
 * Created by awardak on 4/22/17.
 */

public interface IMovieGenreListener {
    void onGenreSuccess(List<Genre> genres);
    void onGenreError(String msg);
}
