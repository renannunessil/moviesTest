package br.com.renannunessil.moviestest.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.databinding.FragmentMoviesListBinding;

public class MoviesListFragment extends Fragment {

    FragmentMoviesListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_list, container, false);
    }
}
