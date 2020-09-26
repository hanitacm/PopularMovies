package com.hanitacm.domain.usecase

import com.hanitacm.domain.model.MovieDomainModel
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor() {
    fun getMovieDetail(id: Int): Single<MovieDomainModel> {
        TODO("Not yet implemented")
    }

}
