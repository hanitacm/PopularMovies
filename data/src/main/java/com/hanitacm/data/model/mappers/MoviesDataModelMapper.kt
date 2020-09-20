package com.hanitacm.data.model.mappers

import com.hanitacm.data.model.MoviesDataModel
import com.hanitacm.domain.model.MovieDomainModel

class MoviesDataModelMapper {
    fun mapToDomainModel(moviesDataModel: MoviesDataModel): List<MovieDomainModel> {
        return moviesDataModel.results.map {
            MovieDomainModel(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
    }
}