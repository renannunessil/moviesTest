package br.com.renannunessil.moviestest;

import android.arch.lifecycle.ViewModelProviders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import br.com.renannunessil.moviestest.model.FavoriteMovie;
import br.com.renannunessil.moviestest.view.MainActivity;
import br.com.renannunessil.moviestest.viewmodel.MoviesListViewModel;

@RunWith(RobolectricTestRunner.class)
public class MoviesListViewModelTest {

    private MoviesListViewModel viewModel;
    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class).get();
        viewModel = ViewModelProviders.of(activity).get(MoviesListViewModel.class);
    }

    @Test
    public void databaseTest() {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setId(1);
        viewModel.saveFavoriteMovie(favoriteMovie);

        List<FavoriteMovie> favoriteMovies = viewModel.getFavoriteMoviesList().getValue();
        Assert.assertEquals(1, favoriteMovies.get(0).getId());
    }

}
