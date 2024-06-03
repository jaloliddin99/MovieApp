package uz.promo.movieapp.ui.home

import uz.promo.movieapp.data.remote.models.Result

data class HomeScreenState(
    val isLoading: Boolean = false,
    val personList: List<Result> = emptyList(),
    val error: String = "",
    val showDialog: Boolean = false
)
