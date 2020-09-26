package com.hanitacm.data.repository.model.mappers

import com.hanitacm.data.repository.model.MovieDataModel
import com.hanitacm.domain.model.MovieDomainModel
import javax.inject.Inject

class MovieDataModelMapper @Inject constructor() {

    fun mapToDomainModel(movies: List<MovieDataModel>): List<MovieDomainModel> {
        return movies.map {
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

    fun mapToDomainModel(movie: MovieDataModel): MovieDomainModel {
        return with(movie) {
            MovieDomainModel(
                id = id,
                title = title,
                overview = overview,
                releaseDate = releaseDate,
                posterPath = posterPath,
                backdropPath = backdropPath,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                popularity = popularity,
                voteAverage = voteAverage
            )
        }
    }
}



