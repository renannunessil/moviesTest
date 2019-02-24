package br.com.renannunessil.moviestest.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.renannunessil.moviestest.model.FavoriteMovie;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(FavoriteMovie... favoriteMovies);

    @Delete
    void deleteMovie(FavoriteMovie... favoriteMovies);

    @Query("SELECT * from favoritemovie")
    LiveData<List<FavoriteMovie>> getFavoriteMovies();
}
