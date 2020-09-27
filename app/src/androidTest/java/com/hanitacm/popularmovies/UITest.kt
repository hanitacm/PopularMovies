package com.hanitacm.popularmovies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import com.hanitacm.popularmovies.di.RepositoryModule
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.Single
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class UITest {
    @Inject
    lateinit var moviesRepository: MoviesRepository

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun show_a_card_list_with_popular_movies() {
        val numMovies = 4

        whenThereArePopularMovies(numMovies)

        activityTestRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(Matchers.not(isDisplayed())))
        onView(withId(R.id.rv_movies)).check(
            matches(
                hasDescendant(
                    allOf(
                        withId(R.id.title),
                        withEffectiveVisibility(Visibility.VISIBLE),
                        withText("Money Plane ${numMovies - 1}")
                    )
                )
            )
        )
    }

    @Test
    fun show_movie_detail_after_selecting_a_movie() {
        val numMovies = 20

        whenThereArePopularMovies(numMovies)

        activityTestRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(Matchers.not(isDisplayed())))
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    numMovies - 1,
                    click()
                )
            )
        onView(withId(R.id.movieTitle)).check(matches(withText("Money Plane ${numMovies - 1}")))
        onView(withId(R.id.country_date)).check(matches(withText("${MovieData.LANGUAGE.toUpperCase()} | ${MovieData.RELEASE_DATE}")))
        onView(withId(R.id.rating)).check(matches(withText("${MovieData.VOTE_AVERAGE}/10")))
        onView(withId(R.id.movieOverview)).check(matches(withText(MovieData.DESCRIPTION)))
    }

    object MovieData {
        const val DESCRIPTION =
            "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals."
        const val RELEASE_DATE = "2020-09-29"
        const val VOTE_AVERAGE = 5.0
        const val LANGUAGE = "en"

    }

    private fun whenThereArePopularMovies(numMovies: Int) {
        var list = mutableListOf<MovieDomainModel>()
        var i = 0
        repeat(numMovies) {
            val movie = MovieDomainModel(
                id = i,
                title = "Money Plane $i",
                overview = MovieData.DESCRIPTION,
                releaseDate = MovieData.RELEASE_DATE,
                posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
                backdropPath = "/gYRzgYE3EOnhUkv7pcbAAsVLe5f.jpg",
                originalLanguage = MovieData.LANGUAGE,
                originalTitle = "Money Plane",
                popularity = 2000.0,
                voteAverage = MovieData.VOTE_AVERAGE
            )

            list.add(i, movie)

            whenever(moviesRepository.getMovieDetail(i)).thenReturn(Single.just(movie))


            i++
        }

        whenever(moviesRepository.getPopularMovies()).thenReturn(Single.just(list))
    }
}