package com.hanitacm.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.annotations.SerializedName


interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MoviesResponse>

}
