package com.beachbody.newreleases.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by awardak on 4/22/17.
 */

public class Movie {
    @SerializedName("poster_path")
    public String posterPath;

    public Boolean adult;

    public String overview;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("genre_ids")
    public List<Integer> genreIds = null;

    /* this is not populated from the API */
    public String genreString;

    public Integer id;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    public String title;

    @SerializedName("backdrop_path")
    public String backdropPath;

    public Double popularity;

    @SerializedName("vote_count")
    public Integer voteCount;

    public Boolean video;

    @SerializedName("vote_average")
    public Double voteAverage;
}
