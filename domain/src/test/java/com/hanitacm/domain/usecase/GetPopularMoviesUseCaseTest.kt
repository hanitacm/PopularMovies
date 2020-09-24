package com.hanitacm.domain.usecase

import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @Spy
    lateinit var moviesRepository: MoviesRepository
    @InjectMocks
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Test
    fun `call moviesRepository`() {
       whenever(moviesRepository.getPopularMovies()).thenReturn(Single.just(moviesDomainModel))

       val repositoryResponse = getPopularMoviesUseCase.getPopularMovies().test()

       verify(moviesRepository, only()).getPopularMovies()

       repositoryResponse.assertResult(UseCaseResult.Success(moviesDomainModel))
        repositoryResponse.assertNoErrors()
    }

    private val moviesDomainModel = listOf(
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