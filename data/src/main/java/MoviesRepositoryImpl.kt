import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.api.data.MoviesResponse
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(private val moviesApi: MoviesApi) : MoviesRepository {

    override fun getPopularMovies(): Single<List<MovieDomainModel>> {
        return moviesApi.getMovies().asMovieDomainModel()

    }
}

fun Single<MoviesResponse>.asMovieDomainModel(): Single<List<MovieDomainModel>> {
    return this.map { it ->
        it.results.map {
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

