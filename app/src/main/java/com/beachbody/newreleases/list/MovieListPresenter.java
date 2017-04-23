package com.beachbody.newreleases.list;

import android.util.Log;

import com.beachbody.newreleases.model.genres.Genre;
import com.beachbody.newreleases.model.movies.Movie;
import com.beachbody.newreleases.network.IMovieGenreListener;
import com.beachbody.newreleases.network.IMovieListListener;
import com.beachbody.newreleases.network.NetworkAdapter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by awardak on 4/22/17.
 */

public class MovieListPresenter implements IMovieListContract.IPresenter,
        IMovieListListener, IMovieGenreListener {
    private static final String TAG = "MovieListPresenter";

    private WeakReference<IMovieListContract.IView> mView;

    public MovieListPresenter(IMovieListContract.IView view) {
        mView = new WeakReference<>(view);
    }

    /* implement IMovieListContract.IPresenter */
    @Override
    public void loadMovies() {
        NetworkAdapter.getInstance(mView.get().getAppContext()).fetchMovies(this);
    }

    @Override
    public void loadGenres() {
        NetworkAdapter.getInstance(mView.get().getAppContext()).fetchGenres(this);
    }

    /* implement IMovieListListener */
    @Override
    public void onMovieListSuccess(List<Movie> list) {
        if (mView.get() != null) {
            mView.get().onMoviesSuccess(list);
        }
    }

    @Override
    public void onMovieListError(String msg) {
        if (mView.get() != null) {
            mView.get().onMoviesError(msg);
        }
    }

    /* implement IMovieGenreListener */
    @Override
    public void onGenreSuccess(List<Genre> genres) {
        if (mView.get() != null) {
            mView.get().onGenresSuccess(genresListToMap(genres));
        }
    }

    @Override
    public void onGenreError(String msg) {
        if (mView.get() != null) {
            mView.get().onGenresError(msg);
        }
    }

    private Map<Integer, String> genresListToMap(List<Genre> genresList) {
        Map<Integer, String> genresMap = new HashMap<>();
        for (Genre genre : genresList) {
            genresMap.put(genre.id, genre.name);
        }
        return genresMap;
    }
}
