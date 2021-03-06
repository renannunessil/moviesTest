package br.com.renannunessil.moviestest.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;


import java.util.ArrayList;
import java.util.List;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.adapter.MoviesListRecyclerViewAdapter;
import br.com.renannunessil.moviestest.databinding.FragmentMoviesListBinding;
import br.com.renannunessil.moviestest.model.FavoriteMovie;
import br.com.renannunessil.moviestest.model.Movie;
import br.com.renannunessil.moviestest.model.MoviesResponse;
import br.com.renannunessil.moviestest.viewmodel.MoviesListViewModel;

public class MoviesListFragment extends Fragment implements MoviesListRecyclerViewAdapter.MoviesListItemClickListener{

    private FragmentMoviesListBinding binding;
    private MoviesListViewModel viewModel;
    private List<MoviesResponse> moviesList = new ArrayList<>();
    private MainActivity activity;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();
    public  MoviesListRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false);
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        context = getContext();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        setSearchClickListener();
        getFavorites();
        binding.etMovieSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE)
                callMovieApi(binding.etMovieSearch.getText().toString(), v);
            return true;
        });
    }

    public interface SelectedMovie {
        void setMovie(Movie movie);
        Movie getMovie();
    }

    private void setSearchClickListener() {
        binding.tvSearchIcon.setOnClickListener(view -> {
            String search = binding.etMovieSearch.getText().toString();
            callMovieApi(search, view);
        });
    }

    private void callMovieApi(String search, View view) {
        hideSoftKeyboard(view);
        binding.tvNotFound.setVisibility(View.GONE);
        activity.showLoading(true);
        getFavorites();
        viewModel.getMoviesList(search).observe(this, response -> {
            activity.showLoading(false);
            if(response != null) {
                if (response.size() > 0) {
                    moviesList = response;
                    setFavorites(favoriteMovies, moviesList);
                    setMoviesListAdapter(moviesList);
                } else {
                    moviesList = new ArrayList<>();
                    setMoviesListAdapter(moviesList);
                    binding.tvNotFound.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setMoviesListAdapter(List<MoviesResponse> moviesList) {
        adapter = new MoviesListRecyclerViewAdapter(moviesList, this);
        layoutManager = new LinearLayoutManager(getContext());
        binding.moviesList.setLayoutManager(layoutManager);
        binding.moviesList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, MoviesResponse movieResponse) {
        activity.setMovie(movieResponse.getMovie());
        activity.setNextFragment(new MovieDetailsFragment());
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void getFavorites() {
        activity.showLoading(true);
        viewModel.getFavoriteMoviesList().observe(this, response -> {
            favoriteMovies = response;
            activity.showLoading(false);
        });
    }

    private void setFavorites(List<FavoriteMovie> favorites, List<MoviesResponse> moviesList) {
        for (FavoriteMovie favorite : favorites) {
            for (MoviesResponse movie : moviesList) {
                if (favorite.getId() == movie.getMovie().getId()) {
                    movie.getMovie().setFavorite(true);
                }
            }
        }
    }
}
