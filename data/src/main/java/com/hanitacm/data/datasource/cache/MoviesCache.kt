package com.hanitacm.data.datasource.cache

import com.hanitacm.data.datasource.cache.model.mapper.MoviesLocalDataModelMapper
import com.hanitacm.data.datasource.db.MoviesDatabase
import com.hanitacm.data.repository.DataSource
import com.hanitacm.data.repository.model.MovieDataModel
import io.reactivex.Single
import javax.inject.Inject

class MoviesCache @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val mapperLocal: MoviesLocalDataModelMapper
) : DataSource {

    override fun getAllMovies(): Single<List<MovieDataModel>> {
        return moviesDatabase.movieDao.getAll().map {
            mapperLocal.mapToDataModel(it)
        }.toSingle()
    }

    fun insertMovies(movies: List<MovieDataModel>): Single<List<Long>> {
        return moviesDatabase.movieDao.insertAll(mapperLocal.mapToLocalDataModel(movies))
    }

}
