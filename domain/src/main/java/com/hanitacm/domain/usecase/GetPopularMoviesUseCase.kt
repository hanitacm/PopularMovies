package com.hanitacm.domain.usecase

import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun getPopularMovies(): Single<UseCaseResult<List<MovieDomainModel>>> {
        return moviesRepository.getPopularMovies().map { UseCaseResult.Success(it) }
    }

}