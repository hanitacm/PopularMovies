package com.hanitacm.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(@Query("api_key") apiKey: String)

}
