package uz.promo.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import uz.promo.movieapp.ui.NavItems
import uz.promo.movieapp.ui.home.HomeRoute
import uz.promo.movieapp.ui.home.HomeViewModel


fun NavGraphBuilder.homeScreen(
    navigateToDetails: () -> Unit,
    viewModel: HomeViewModel
) {
    composable(
        route = NavItems.Home.screenRoute,
    ) {
        HomeRoute(
            navigateToDetails,
            viewModel
        )
    }
}
