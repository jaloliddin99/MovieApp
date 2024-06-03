package uz.promo.movieapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import uz.promo.movieapp.TopAppBar
import uz.promo.movieapp.ui.home.HomeViewModel
import uz.promo.movieapp.ui.navigation.detailsScreen
import uz.promo.movieapp.ui.navigation.homeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(
    appState: ApplicationState = rememberMovieAppState(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val destination = appState.currentTopLevelDestination

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            if (destination != null) {
                val showBackArrow = destination.screenRoute == NavItems.Details.screenRoute
                val isHomePage = destination.screenRoute == NavItems.Home.screenRoute

                TopAppBar(
                    title = destination.title,
                    navigationIcon = if (showBackArrow) Icons.Filled.ArrowBack else null,
                    navigationIconContentDescription = null,
                    actionIcon = if (isHomePage) Icons.Filled.FilterList else null,
                    actionIconContentDescription = null,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    onActionClick = {
                        homeViewModel.showDialog(true)
                    },
                    onNavigationClick = {
                        if (showBackArrow) {
                            appState.navController.popBackStack()
                        } else {
                            appState.navigateToSearch()
                        }
                    }
                )
            }
            NavigationGraph(appState, homeViewModel)
        }
    }
}



@Composable
fun NavigationGraph(
    appState: ApplicationState,
    viewModel: HomeViewModel
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = NavItems.Home.screenRoute
    ) {
        homeScreen(
            navigateToDetails = {
                navController.navigate(NavItems.Details.screenRoute)
            },
            viewModel = viewModel
        )
        detailsScreen(
            navigateBack = navController::popBackStack,
            homeViewModel = viewModel
        )
    }
}