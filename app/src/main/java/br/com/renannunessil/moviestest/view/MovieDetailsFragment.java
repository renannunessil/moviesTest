package br.com.renannunessil.moviestest.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.databinding.FragmentMovieDetailsBinding;
import br.com.renannunessil.moviestest.model.Movie;
import br.com.renannunessil.moviestest.viewmodel.MoviesListViewModel;

public class MovieDetailsFragment extends Fragment {

    private MainActivity activity;
    private FragmentMovieDetailsBinding binding;
    private Movie movie;
    private MoviesListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        activity = (MainActivity) getActivity();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movie = activity.getMovie();
        binding.setMovie(movie);
        if (movie.getPoster() != null) {
            Picasso.get().load(movie.getPoster().getOriginalImage()).fit().into(binding.ivMoviePoster);
        } else {
            Picasso.get().load(R.drawable.not_available).fit().into(binding.ivMoviePoster);
        }
        setFavoriteClickListener();
    }

    private void setFavoriteClickListener() {
        TextView favoriteIcon = binding.tvFavoriteIcon;
        favoriteIcon.setOnClickListener(v -> {
            favoriteChange(favoriteIcon);
        });
    }

    private void favoriteChange(TextView favoriteIcon) {
        if (movie.isFavorite()) {
            favoriteIcon.setTextColor(getContext().getColor(R.color.unfavoriteColor));
            movie.setFavorite(false);
            viewModel.unfavoriteMovie(movie.favoriteMovieParse());
        } else {
                favoriteIcon.setTextColor(getContext().getColor(R.color.favoriteColor));
                movie.setFavorite(true);
                viewModel.saveFavoriteMovie(movie.favoriteMovieParse());
        }
    }

}
