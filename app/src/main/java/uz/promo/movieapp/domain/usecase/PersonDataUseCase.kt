package uz.promo.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.promo.movieapp.data.remote.models.ModelMovieResponse
import uz.promo.movieapp.data.remote.models.Result
import uz.promo.movieapp.domain.repository.NetworkRepository
import uz.promo.movieapp.domain.state.Resource
import java.io.IOException
import javax.inject.Inject

class PersonDataUseCase @Inject constructor(
    private val repository: NetworkRepository
) {
    operator fun invoke(): Flow<Resource<List<Result>>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    repository.fetchPersons().results
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
        catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

}