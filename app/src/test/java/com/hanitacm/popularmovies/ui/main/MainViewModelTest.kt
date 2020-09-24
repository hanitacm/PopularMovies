package com.hanitacm.popularmovies.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.usecase.GetPopularMoviesUseCase
import com.hanitacm.popularmovies.RxSchedulerRule
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RxSchedulerRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @InjectMocks
    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var observer: Observer<in MainViewModelState>

    @Before
    fun setUp() {
        mainViewModel.viewState.observeForever(observer)
    }

    @Test
    fun `call getPopularMoviesUseCase`() {
        whenever(getPopularMoviesUseCase.getPopularMovies()).thenReturn(
            Single.just(
                UseCaseResult.Success(
                    listPopularMovies
                )
            )
        )

        mainViewModel.getPopularMovies()

        observer.onChanged(MainViewModelState.Loading)
        observer.onChanged(MainViewModelState.MoviesLoaded(listPopularMovies))

    }

    @Test
    fun `returns error if it is not possible get popular movies list`() {
        val error = Exception()

        whenever(getPopularMoviesUseCase.getPopularMovies()).thenReturn(
            Single.just(
                UseCaseResult.Error<Exception>(
                    error
                )
            )
        )

        mainViewModel.getPopularMovies()

        observer.onChanged(MainViewModelState.MoviesLoadFailure(error))
    }

    private val listPopularMovies = listOf(
        MovieDomainModel(
            popularity = 2000.0,
            voteAverage = 0.0,
            overview = "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
            posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            releaseDate = "2020-09-29",
            title = "Money Plane",
            originalTitle = "Money Plane",
            originalLanguage = "en",
            backdropPath = "/gYRzgYE3EOnhUkv7pcbAAsVLe5f.jpg",
            id = 694919
        )
    )
}




