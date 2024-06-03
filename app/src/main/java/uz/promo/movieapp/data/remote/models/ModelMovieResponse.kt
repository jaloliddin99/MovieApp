package uz.promo.movieapp.data.remote.models

data class ModelMovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)