package com.hanitacm.data

import MoviesRepositoryImpl
import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.api.model.MovieData
import com.hanitacm.data.datasource.api.model.MoviesDataModel
import com.hanitacm.data.datasource.api.model.mappers.MoviesDataModelMapper
import com.hanitacm.domain.model.MovieDomainModel
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var moviesApi: MoviesApi

    @Spy
    lateinit var moviesDataModelMapper: MoviesDataModelMapper

    @InjectMocks
    lateinit var moviesRepository: MoviesRepositoryImpl


    @Test
    fun `get popular movies from api`() {
        whenever(moviesApi.getMovies()).thenReturn(Single.just(moviesResponse))

        val apiResponse = moviesRepository.getPopularMovies().test()

        apiResponse.assertNoErrors()

        verify(moviesApi, only()).getMovies()
        verify(moviesDataModelMapper).mapToDomainModel(moviesResponse)

        apiResponse.assertValue(moviesDomainModel)
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

    private val moviesResponse =
        MoviesDataModel(
            page = 1,
            totalPages = 500,
            totalResults = 10000,
            results = listOf(
                MovieData(
                    popularity = 2000.0,
                    voteCount = 0,
                    video = false,
                    posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
                    id = 694919,
                    adult = false,
                    backdropPath = "/gYRzgYE3EOnhUkv7pcbAAsVLe5f.jpg",
                    originalLanguage = "en",
                    originalTitle = "Money Plane",
                    genreIds = listOf(28),
                    title = "Money Plane",
                    voteAverage = 0.0,
                    overview = "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
                    releaseDate = "2020-09-29"

                )
            )
        )


}