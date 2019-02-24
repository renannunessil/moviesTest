package br.com.renannunessil.moviestest.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.model.Movie;

public class MainActivity extends AppCompatActivity implements MoviesListFragment.SelectedMovie {

    private FragmentManager fragmentManager;
    private Movie movie;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        setNextFragment(null);
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public Movie getMovie() {
        return this.movie;
    }

    public void setNextFragment(Fragment fragment) {
        if (fragment == null) fragment = new MoviesListFragment();
        currentFragment = fragment;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.frame_layout, fragment);
        ft.addToBackStack("");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }
    }

    public void showLoading(Boolean show) {
        ProgressBar progressBar = findViewById(R.id.loading);
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
