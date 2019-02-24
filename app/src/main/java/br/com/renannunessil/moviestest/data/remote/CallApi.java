package br.com.renannunessil.moviestest.data.remote;

import java.util.List;

import br.com.renannunessil.moviestest.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallApi {

    @GET("shows")
    Call<List<MoviesResponse>> getMovies(@Query("q") String movieSearch);

}
