package br.com.renannunessil.moviestest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import br.com.renannunessil.moviestest.data.local.AppDataBase;
import br.com.renannunessil.moviestest.data.remote.CallApi;
import br.com.renannunessil.moviestest.data.remote.RetrofitClientInstance;
import br.com.renannunessil.moviestest.model.FavoriteMovie;
import br.com.renannunessil.moviestest.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviesListRepository {

    private static MoviesListRepository instance;
    private Retrofit retrofit;
    private AppDataBase appDataBase;

    public MoviesListRepository(AppDataBase appDataBase) {
        this.retrofit = RetrofitClientInstance.getRetrofitInstance();
        this.appDataBase = appDataBase;
    }

    public static MoviesListRepository getInstance(AppDataBase appDataBase){
        if (instance == null) {
            synchronized (MoviesListRepository.class) {
                if (instance == null) {
                    instance = new MoviesListRepository(appDataBase);
                }
            }
        }
        return instance;
    }

    public LiveData<List<MoviesResponse>> getMoviesList(String movieSearch) {
        Log.e("chamada", "ChamouApi");
        CallApi callApi = retrofit.create(CallApi.class);
        final MutableLiveData<List<MoviesResponse>> moviesListResponseLiveData = new MutableLiveData<>();
        callApi.getMovies(movieSearch).enqueue(new Callback<List<MoviesResponse>>() {
            @Override
            public void onResponse(Call<List<MoviesResponse>> call, Response<List<MoviesResponse>> response) {
                moviesListResponseLiveData.setValue(response.body());
                Log.e("response", response.toString());
            }

            @Override
            public void onFailure(Call<List<MoviesResponse>> call, Throwable t) {
                moviesListResponseLiveData.setValue(null);
                Log.e("response", t.getMessage());
            }
        });
        return moviesListResponseLiveData;
    }

    public LiveData<List<FavoriteMovie>> getFavoriteMovies() {
        LiveData<List<FavoriteMovie>> favoriteMovieLiveData;
        favoriteMovieLiveData = appDataBase.movieDao.getFavoriteMovies();
        return favoriteMovieLiveData;
    }

    public void saveFavoriteMovie(FavoriteMovie favoriteMovie) {
        appDataBase.movieDao.save(favoriteMovie);
    }

    public void unfavoriteMovie(FavoriteMovie favoriteMovie) {
        appDataBase.movieDao.deleteMovie(favoriteMovie);
    }

}
