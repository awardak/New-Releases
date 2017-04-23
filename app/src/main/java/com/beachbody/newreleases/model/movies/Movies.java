package com.beachbody.newreleases.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by awardak on 4/22/17.
 */

public class Movies {
    public Integer page;

    @SerializedName("results")
    public List<Movie> movieList;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;
}
