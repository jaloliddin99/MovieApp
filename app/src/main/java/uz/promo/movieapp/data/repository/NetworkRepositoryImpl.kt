package uz.promo.movieapp.data.repository


import uz.promo.movieapp.data.remote.ApiInterface
import uz.promo.movieapp.data.remote.models.ModelMovieResponse
import uz.promo.movieapp.domain.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : NetworkRepository {

    override suspend fun fetchPersons(): ModelMovieResponse {
        return apiInterface.gerPersons()
    }


}