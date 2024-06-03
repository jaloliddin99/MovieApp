package uz.promo.movieapp.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import uz.promo.movieapp.ui.home.filter.BottomSheetContent
import uz.promo.movieapp.ui.theme.MovieAppTheme
import uz.promo.movieapp.utils.FreeLoading


@Composable
fun HomeRoute(
    navigateToDetails: () -> Unit,
    viewModel: HomeViewModel
) {
    HomeScreen(
        navigateToDetails = navigateToDetails,
        viewModel= viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: () -> Unit,
    viewModel: HomeViewModel
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val state = viewModel.state.value
    val filteredList = viewModel.filteredList.value
    val isFeedLoading = state.isLoading

    var searchTextListener by remember {
        mutableStateOf("")
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)


    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                SearchTextField(
                    onSearchQueryChanged = { query ->
                        searchTextListener = query
                        viewModel.searchPersons(query)
                    },
                    onSearchTriggered = { query ->
                        searchTextListener = query
                        viewModel.searchPersons(query)
                    },
                    searchQuery = searchTextListener
                )
            }

            items(filteredList) { person ->
                SwipeBox(
                    onDislike = {
                         viewModel.updateResultByLike(false, person.id)
                    },
                    onLike = {
                        viewModel.updateResultByLike(true, person.id)
                    },
                    modifier = Modifier.animateItemPlacement()
                ) {
                    CelebrityItem(
                        result = person,
                        celebrityItemClicked = {
                            navigateToDetails()
                            viewModel.updateUserData(person)
                        }
                    )
                }

            }
        }
        FreeLoading(isFeedLoading = isFeedLoading)
    }



    if (state.showDialog) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.showDialog(false)
            },
            sheetState = sheetState
        ) {
            BottomSheetContent(
                onClickListen = { filter ->
                    viewModel.filterList(filter)
                    viewModel.showDialog(false)
                },
                preselectedValues = viewModel.getFilter()
            )
        }
    }
}

