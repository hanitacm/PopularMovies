package com.hanitacm.popularmovies.ui.detail

import com.hanitacm.domain.model.MovieDomainModel

sealed class DetailViewModelState {
    object Loading : DetailViewModelState()
    data class DetailLoaded(val movie: MovieDomainModel) : DetailViewModelState()
    data class DetailLoadFailure(val error: Exception) : DetailViewModelState()

}
