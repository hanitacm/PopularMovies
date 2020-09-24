import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.datasource.cache.MoviesCache
import com.hanitacm.data.datasource.api.model.mappers.MoviesDataModelMapper
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDataModelMapper: MoviesDataModelMapper,
    private val moviesCache: MoviesCache,
    private val moviesDbModelMapper: Moviesdb
) : MoviesRepository {

    override fun getPopularMovies(): Single<List<MovieDomainModel>> {
        return moviesCache.isCached().flatMap { cached ->
            if (cached) {
                moviesCache.getAllMovies().map {  }
            } else {

                moviesApi.getMovies().map { moviesDataModelMapper.mapToDomainModel(it) }
            }

        }


    }
}


