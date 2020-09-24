package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.MoviesDataModel
import io.reactivex.Single
import javax.inject.Inject

class MoviesApi @Inject constructor(private val moviesService: MoviesService) {
    fun getMovies(): Single<MoviesDataModel> {
        return moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16")
    }
}