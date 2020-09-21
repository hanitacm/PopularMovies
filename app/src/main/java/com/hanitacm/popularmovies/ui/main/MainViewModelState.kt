package com.hanitacm.popularmovies.ui.main

import com.hanitacm.domain.model.MovieDomainModel

sealed class MainViewModelState {
    object Loading : MainViewModelState()
    data class MoviesLoaded(val movies: List<MovieDomainModel>) : MainViewModelState()
    data class MoviesLoadFailure(val error: Exception) : MainViewModelState()

}