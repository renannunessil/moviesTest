package br.com.renannunessil.moviestest;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.renannunessil.moviestest.view.MainActivity;
import br.com.renannunessil.moviestest.view.MoviesListFragment;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class MoviesListFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void uiTest() throws InterruptedException {
        onView(withId(R.id.et_movie_search)).perform(clearText(), typeText("rick"));
        onView(withId(R.id.tv_search_icon)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.movies_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
