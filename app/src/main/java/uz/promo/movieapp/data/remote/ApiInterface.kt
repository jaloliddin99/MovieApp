package uz.promo.movieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uz.promo.movieapp.data.remote.models.ModelMovieResponse
import uz.promo.movieapp.BuildConfig


interface ApiInterface {

    @GET("3/person/popular")
    suspend fun gerPersons(
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ModelMovieResponse


}