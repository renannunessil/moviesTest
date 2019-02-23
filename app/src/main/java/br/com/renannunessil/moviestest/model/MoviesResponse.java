package br.com.renannunessil.moviestest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesResponse {
    @SerializedName("show")
    @Expose
    Movie movie;

    public MoviesResponse() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
