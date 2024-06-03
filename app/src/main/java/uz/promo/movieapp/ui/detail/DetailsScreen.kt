package uz.promo.movieapp.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.promo.movieapp.ui.home.CelebItemDetails
import uz.promo.movieapp.ui.home.HomeViewModel


@Composable
fun DetailsRoute(
    navigateBack: () -> Unit,
    homeViewModel: HomeViewModel
) {
    DetailsScreen(navigateBack, homeViewModel)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(navigateBack: () -> Unit,
                  homeViewModel: HomeViewModel) {
    val userData = homeViewModel.userData.collectAsState().value
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    val scrollState = rememberScrollState()

    val pagerState = rememberPagerState(pageCount = {
        userData.known_for.size
    })
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = paddingValues.calculateBottomPadding())){
        TopShadow()
        Column {
            ImagePager(userData.known_for, pagerState)
            CelebItemDetails(result = userData)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Overview",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = userData.known_for.getOrNull(0)?.overview ?:"",
                modifier = Modifier.padding(horizontal = 16.dp))
        }
        DetailsToolbar(onBackClick = navigateBack)
    }
}