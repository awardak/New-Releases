package com.beachbody.newreleases;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.beachbody.newreleases.list.MovieListView;

public class MainActivity extends AppCompatActivity {

    private MovieListView mMovieListFragment;
    private static final String MOVIE_LIST_FRAGMENT = "MovieListFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        mMovieListFragment = (MovieListView) fm.findFragmentByTag(MOVIE_LIST_FRAGMENT);

        // create the fragment and data the first time
        if (mMovieListFragment == null) {
            mMovieListFragment = new MovieListView();
            fm.beginTransaction()
                    .add(R.id.list_container, mMovieListFragment, MOVIE_LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // if this activity is finishing, clear the back stack as well
        if (isFinishing()) {
            getSupportFragmentManager()
                    .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
