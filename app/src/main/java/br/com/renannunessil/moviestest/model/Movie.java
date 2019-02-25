package br.com.renannunessil.moviestest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String title;
    @SerializedName("summary")
    private String summary;
    @SerializedName("premiered")
    private String premiereDate;
    @SerializedName("image")
    private Images poster;
    @SerializedName("genres")
    private List<String> genre;
    private boolean isFavorite;

    public Movie() {
        this.genre = new ArrayList<>();
        this.isFavorite = false;
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

    public Images getPoster() {
        return poster;
    }

    public void setPoster(Images poster) {
        this.poster = poster;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public String getFormattedDate() {
        String date = this.premiereDate;
        date.replace("-", "/");
        return date;
    }

    public String getFormattedSummary() {
        String summary = this.summary;
        summary = summary.replace("<p>","")
                         .replace("<b>", "")
                         .replace("</p>", "")
                         .replace("</b>", "");

        return summary;
    }

    public String getGenreLabel() {
        String label = "";
        for (int i = 0; i < genre.size(); i++) {
            if (i != genre.size()-1) {
                label += genre.get(i) + " / ";
            } else {
                label += genre.get(i);
            }
        }
        return label;
    }

    public FavoriteMovie favoriteMovieParse() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setId(this.id);
        return favoriteMovie;
    }
}
