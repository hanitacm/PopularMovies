package com.hanitacm.data.datasource.api

import com.hanitacm.data.datasource.api.model.mappers.MoviesDataModelMapper
import com.hanitacm.data.repository.MovieDataModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class MoviesApi @Inject constructor(
    private val moviesService: MoviesService,
    private val mapper: MoviesDataModelMapper
) {
    fun getMovies(): Single<List<MovieDataModel>> {
        return moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16").map {
            if (it.totalResults > 0)
                mapper.mapToDataModel(it)
            else
               emptyList()
        }
    }
}