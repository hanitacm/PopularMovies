package com.hanitacm.domain.usecase

import com.hanitacm.domain.UseCaseResult
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    fun getMovieDetail(id: Int): Single<UseCaseResult<MovieDomainModel>> {
        return moviesRepository.getMovieDetail(id).map { UseCaseResult.Success(it) }
    }

}
