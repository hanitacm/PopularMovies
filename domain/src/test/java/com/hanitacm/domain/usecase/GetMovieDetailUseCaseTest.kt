package com.hanitacm.domain.usecase

import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {
    @Mock
    lateinit var moviesRepository: MoviesRepository

    @InjectMocks
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Test
    fun `get movie detail from repository`() {
        val id = 2222

        whenever(moviesRepository.getMovieDetail(id)).thenReturn(Single.just(movieDomainModel))

        getMovieDetailUseCase.getMovieDetail(id)

        verify(moviesRepository,only()).getMovieDetail(id)
    }


    private val movieDomainModel =
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
            id = 2222

        )
}