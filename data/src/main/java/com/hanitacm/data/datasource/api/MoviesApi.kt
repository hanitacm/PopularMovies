package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.data.MoviesResponse
import io.reactivex.Single

class MoviesApi(private val moviesService: MoviesService) {
    fun getMovies(): Single<MoviesResponse> {
        return moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16")
    }
}