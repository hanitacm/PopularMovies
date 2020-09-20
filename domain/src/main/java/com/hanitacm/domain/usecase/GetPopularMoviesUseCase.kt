package com.hanitacm.domain.usecase

import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

    fun getPopularMovies(): Single<List<MovieDomainModel>> {
        return moviesRepository.getPopularMovies()
    }

}