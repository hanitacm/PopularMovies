package com.hanitacm.data.repository

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesCache: MoviesCache,
    private val mapper: MovieDataModelMapper
) : MoviesRepository {

    override fun getPopularMovies(): Single<List<MovieDomainModel>> {
        return Single.concat(moviesCache.getAllMovies(), moviesApi.getMovies())
            .filter { it.isNotEmpty() }
            .firstOrError()
            .doOnSuccess { moviesCache.insertMovies(it) }
            .map { mapper.mapToDomainModel(it) }
    }
}






