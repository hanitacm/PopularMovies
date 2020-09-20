package com.hanitacm.data.datasource.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import com.hanitacm.data.datasource.api.data.MoviesResponse


interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MoviesResponse>

}
