package com.hanitacm.data.datasource.cache

import com.hanitacm.data.datasource.db.Movie
import com.hanitacm.data.datasource.db.MoviesDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MoviesCache @Inject constructor(private val moviesDatabase: MoviesDatabase) {

    fun getAllMovies(): Flowable<List<Movie>> {
        return moviesDatabase.movieDao.getAll()
    }

    fun insertMovies(movies: List<Movie>): Completable {
        return moviesDatabase.movieDao.insertAll(movies)
    }

    fun isCached(): Single<Boolean> {
        return moviesDatabase.movieDao.getAll().isEmpty
    }
}
