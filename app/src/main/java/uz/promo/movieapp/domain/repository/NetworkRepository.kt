package uz.promo.movieapp.domain.repository

import uz.promo.movieapp.data.remote.models.ModelMovieResponse

interface NetworkRepository {

    suspend fun fetchPersons(): ModelMovieResponse

}