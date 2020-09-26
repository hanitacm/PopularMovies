package com.hanitacm.domain.repository

import com.hanitacm.domain.model.MovieDomainModel
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<MovieDomainModel>>
    fun getMovieDetail(id: Int): Single<MovieDomainModel>
}