package br.com.renannunessil.moviestest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.renannunessil.moviestest.data.local.AppDataBase;
import br.com.renannunessil.moviestest.model.FavoriteMovie;
import br.com.renannunessil.moviestest.model.MoviesResponse;
import br.com.renannunessil.moviestest.repository.MoviesListRepository;

public class MoviesListViewModel extends AndroidViewModel {

    private MoviesListRepository repository;
    private MutableLiveData<List<FavoriteMovie>> favoriteMovies = new MutableLiveData<>();

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        repository = MoviesListRepository.getInstance(AppDataBase.getAppDatabase(application));
    }

    public LiveData<List<MoviesResponse>> getMoviesList(String movieSearch) {
        return repository.getMoviesList(movieSearch);
    }

    private void getFavoriteMovies() {
        favoriteMovies.setValue(repository.getFavoriteMovies().getValue());
    }

    public void saveFavoriteMovie(FavoriteMovie favoriteMovie) {
        repository.saveFavoriteMovie(favoriteMovie);
        getFavoriteMovies();
    }

    public void unfavoriteMovie(FavoriteMovie favoriteMovie) {
        repository.unfavoriteMovie(favoriteMovie);
        getFavoriteMovies();
    }

    public LiveData<List<FavoriteMovie>> getFavoriteMoviesList() {
        getFavoriteMovies();
        return favoriteMovies;
    }

}
