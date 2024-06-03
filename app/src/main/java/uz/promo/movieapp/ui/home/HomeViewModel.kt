package uz.promo.movieapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.promo.movieapp.data.remote.models.Result
import uz.promo.movieapp.data.remote.models.createTestResult
import uz.promo.movieapp.domain.state.Resource
import uz.promo.movieapp.domain.usecase.PersonDataUseCase
import uz.promo.movieapp.ui.home.filter.ModelFilter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PersonDataUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _filteredList = mutableStateOf<List<Result>>(emptyList())
    val filteredList: State<List<Result>> = _filteredList

    init {
        getAllPersons()
    }

    fun getAllPersons() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val results = result.data ?: emptyList()
                    _state.value = HomeScreenState(personList = results)
                    _filteredList.value = results
                }

                is Resource.Error -> {
                    _state.value = HomeScreenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun showDialog(show: Boolean) {
        _state.value = _state.value.copy(showDialog = show)
    }


    fun searchPersons(query: String) {
        _filteredList.value = if (query.isEmpty()) {
            _state.value.personList
        } else {
            _state.value.personList.filter {
                it.name.contains(query, ignoreCase = true) || it.original_name.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
    }

    fun updateResultByLike(isLiked: Boolean, resultId: Int){
        _filteredList.value =  updateResults(isLiked, resultId, _filteredList.value)
    }

    private fun updateResults(isLiked: Boolean, resultId: Int, list: List<Result>): List<Result>{
        return list.map { result ->
            if (result.id == resultId) {
                result.copy(isLiked = isLiked)
            } else {
                result
            }
        }
    }

    private val tvMovieMap = hashMapOf(
        1 to "tv",
        2 to "movie",
    )

    private var modelFilter = ModelFilter()
    fun getFilter(): ModelFilter{
        return modelFilter
    }

    fun filterList(filter: ModelFilter) {
        _filteredList.value = filterResults(
            results = _state.value.personList,
            gender = filter.gender.ordinal,
            mediaType = tvMovieMap[filter.mediaType.ordinal],
            languages = filter.lang
        )
        modelFilter = filter
    }


    private fun filterResults(
        results: List<Result>,
        gender: Int,
        mediaType: String?,
        languages: List<String>
    ): List<Result> {
        return results.filter { result ->
            (gender == 0 || result.gender == gender) && result.known_for.any { knownFor ->
                (mediaType == null || knownFor.media_type == mediaType) &&
                        (languages.isEmpty() || languages.contains(knownFor.original_language))
            }
        }
    }



    private val _userData = MutableStateFlow(createTestResult())
    val userData = _userData.asStateFlow()

    fun updateUserData(newUser: Result) {
        _userData.value = newUser
    }


}