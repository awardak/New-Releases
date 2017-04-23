package com.beachbody.newreleases.list;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beachbody.newreleases.R;
import com.beachbody.newreleases.model.movies.Movie;
import com.beachbody.newreleases.network.NetworkAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by awardak on 4/22/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListItemViewHolder> {

    private Fragment mViewContext;
    private List<Movie> mMovieList;
    private Map<Integer, String> mGenresMap;

    public MovieListAdapter(Fragment viewContext) {
        mViewContext = viewContext;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
        if (mGenresMap != null) {
            populateGenreString();
        }
    }

    public void setGenresMap(Map<Integer, String> genresMap) {
        mGenresMap = genresMap;
        if (mMovieList != null) {
            populateGenreString();
        }
    }

    private void populateGenreString() {
        for (Movie movie : mMovieList) {
            if (movie.genreString != null) {
                return;
            }
            movie.genreString = "";
            for (int i = 0; i < movie.genreIds.size(); i++) {
                if (i > 0) movie.genreString += ", ";
                movie.genreString += mGenresMap.get(movie.genreIds.get(i));
            }
        }
    }

    @Override
    public MovieListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListItemViewHolder holder, int position) {
        holder.bindMovie(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieListItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mPosterImage;
        private TextView mTitleView;
        private TextView mGenreView;

        public MovieListItemViewHolder(View itemView) {
            super(itemView);
            mPosterImage = (ImageView) itemView.findViewById(R.id.movie_boxart);
            mTitleView = (TextView) itemView.findViewById(R.id.movie_title);
            mGenreView = (TextView) itemView.findViewById(R.id.movie_genre);
            itemView.setOnClickListener(this);
        }

        public void bindMovie(Movie movie) {
            NetworkAdapter.fetchImage(mViewContext, movie.posterPath, mPosterImage);
            mTitleView.setText(movie.title);
            mGenreView.setText(movie.genreString);
        }

        @Override
        public void onClick(View view) {
            // TODO
            Toast.makeText(view.getContext(), mTitleView.getText() + "\n details...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
