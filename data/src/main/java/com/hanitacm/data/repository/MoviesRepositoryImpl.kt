package com.hanitacm.data.repository

import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.repository.model.mappers.MovieDataModelMapper
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
        return moviesCache.getAllMovies()
            .flatMap { movies ->
                when {
                    movies.isEmpty() -> moviesApi.getAllMovies()
                        .doOnSuccess { moviesCache.insertMovies(it) }
                    else -> Single.just(movies)
                }
            }.map { mapper.mapToDomainModel(it) }
    }

    override fun getMovieDetail(id: Int): Single<MovieDomainModel> {
        return moviesCache.getMovieDetail(id).map { mapper.mapToDomainModel(it) }
    }
}






