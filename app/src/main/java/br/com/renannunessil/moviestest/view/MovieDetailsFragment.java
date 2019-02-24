package br.com.renannunessil.moviestest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.databinding.FragmentMovieDetailsBinding;
import br.com.renannunessil.moviestest.model.Movie;

public class MovieDetailsFragment extends Fragment {

    private MainActivity activity;
    private FragmentMovieDetailsBinding binding;
    private Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
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
    }


}
