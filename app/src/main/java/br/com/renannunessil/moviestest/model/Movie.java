package br.com.renannunessil.moviestest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("name")
    private String title;
    @SerializedName("summary")
    private String summary;
    @SerializedName("premiered")
    private String premiereDate;
    @SerializedName("image")
    private List<String> poster;
    @SerializedName("genres")
    private List<String> genre;

    public Movie() {
        this.poster = new ArrayList<>();
        this.genre = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(String premiereDate) {
        this.premiereDate = premiereDate;
    }

    public List<String> getPoster() {
        return poster;
    }

    public void setPoster(List<String> poster) {
        this.poster = poster;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
