package com.hanitacm.data

class MoviesApi(private val moviesService: MoviesService) {
    fun getMovies() {
        moviesService.getPopularMovies("4b2dda035db530ab9de5426133354f16")
    }
}