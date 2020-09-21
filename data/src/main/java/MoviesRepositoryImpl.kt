import com.hanitacm.data.datasource.api.MoviesApi
import com.hanitacm.data.model.mappers.MoviesDataModelMapper
import com.hanitacm.domain.model.MovieDomainModel
import com.hanitacm.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDataModelMapper: MoviesDataModelMapper
) : MoviesRepository {

    override fun getPopularMovies(): Single<List<MovieDomainModel>> {
        return moviesApi.getMovies().map { moviesDataModelMapper.mapToDomainModel(it) }

    }
}


