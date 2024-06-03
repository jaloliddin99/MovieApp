package uz.promo.movieapp.ui.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import uz.promo.movieapp.BuildConfig
import uz.promo.movieapp.R
import uz.promo.movieapp.data.remote.models.KnownFor
import uz.promo.movieapp.data.remote.models.Result

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    result: List<KnownFor>,
    pagerState: PagerState
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var isError by remember {
        mutableStateOf(false)
    }


    val suffix = result[pagerState.currentPage].poster_path

    val prefix = BuildConfig.IMAGE_URL

    val url = "$prefix${suffix}"

    val imageLoader = rememberAsyncImagePainter(model = url,
        onState = { statePager ->
            isLoading = statePager is AsyncImagePainter.State.Loading
            isError = statePager is AsyncImagePainter.State.Error
        })

    Box(
        contentAlignment = Alignment.BottomCenter,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            Box {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(80.dp),
                    )
                }
                Image(
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = if (isError.not()) imageLoader else painterResource(R.drawable.ic_launcher_background),
                )
            }
        }

        CircularPagerIndicator(
            itemCount = pagerState.pageCount,
            currentPage = pagerState.currentPage
        )
    }
}

@Composable
fun CircularPagerIndicator(
    modifier: Modifier = Modifier,
    itemCount: Int,
    currentPage: Int,
    indicatorSize: Dp = 8.dp,
    indicatorColor: Color = Color.Gray

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            items(itemCount) { index ->
                CircularIndicator(
                    selected = index == currentPage,
                    size = indicatorSize,
                    color = indicatorColor,
                    selectedSize = indicatorSize * 1.1f
                )
            }
        }
    }
}

@Composable
fun CircularIndicator(
    selected: Boolean,
    size: Dp,
    color: Color,
    selectedSize: Dp
) {
    val finalSize = if (selected) selectedSize else size
    val selectedColor = if (selected) Color.White else color

    Canvas(
        modifier = Modifier
            .padding(1.2.dp)
            .size(finalSize)
    ) {
        drawRoundRect(
            color = selectedColor,
            size = Size(finalSize.toPx(), finalSize.toPx()),
            cornerRadius = CornerRadius(50f)
        )
    }
}