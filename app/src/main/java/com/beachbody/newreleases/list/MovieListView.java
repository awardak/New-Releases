package com.beachbody.newreleases.list;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beachbody.newreleases.R;
import com.beachbody.newreleases.model.movies.Movie;

import java.util.List;
import java.util.Map;

public class MovieListView extends Fragment implements IMovieListContract.IView {

    private IMovieListContract.IPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    public MovieListView() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MovieListPresenter(this);

        // retain this fragment across Activity re-creation
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MovieListAdapter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgressDialog = ProgressDialog.show(getActivity(), "Loading movies...", null);
        mPresenter.loadMovies();
        mPresenter.loadGenres();
    }

    /* implement IMovieListContract.IView */
    @Override
    public void onMoviesSuccess(List<Movie> movies) {
        mProgressDialog.dismiss();
        mAdapter.setMovieList(movies);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMoviesError(String msg) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), "Unable to load movies: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGenresSuccess(Map<Integer, String> genres) {
        mAdapter.setGenresMap(genres);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGenresError(String msg) {

    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }
}
