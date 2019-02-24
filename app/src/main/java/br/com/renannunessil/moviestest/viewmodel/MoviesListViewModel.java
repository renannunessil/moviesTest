package br.com.renannunessil.moviestest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.renannunessil.moviestest.data.local.AppDataBase;
import br.com.renannunessil.moviestest.model.FavoriteMovie;
import br.com.renannunessil.moviestest.model.MoviesResponse;
import br.com.renannunessil.moviestest.repository.MoviesListRepository;

public class MoviesListViewModel extends AndroidViewModel {

    private MoviesListRepository repository;

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        repository = MoviesListRepository.getInstance(AppDataBase.getAppDatabase(application));
    }

    public LiveData<List<MoviesResponse>> getMoviesList(String movieSearch) {
        return repository.getMoviesList(movieSearch);
    }

    public LiveData<List<FavoriteMovie>> getFavoriteMovies(int id) {
        return repository.getFavoriteMovies();
    }

    public void saveFavoriteMovie(FavoriteMovie favoriteMovie) {
        repository.saveFavoriteMovie(favoriteMovie);
    }

    public void unfavoriteMovie(FavoriteMovie favoriteMovie) {
        repository.unfavoriteMovie(favoriteMovie);
    }

}
