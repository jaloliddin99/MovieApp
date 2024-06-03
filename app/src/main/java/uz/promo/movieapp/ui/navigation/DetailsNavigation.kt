package uz.promo.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import uz.promo.movieapp.ui.NavItems
import uz.promo.movieapp.ui.detail.DetailsRoute
import uz.promo.movieapp.ui.home.HomeViewModel


fun NavGraphBuilder.detailsScreen(
    navigateBack: () -> Unit,
    homeViewModel: HomeViewModel
) {
    composable(
        route = NavItems.Details.screenRoute,
    ) {
        DetailsRoute(
            navigateBack,
            homeViewModel
        )
    }
}
