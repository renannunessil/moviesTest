package br.com.renannunessil.moviestest.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class FavoriteMovie {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int id;

    public FavoriteMovie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
