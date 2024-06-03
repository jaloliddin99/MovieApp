package uz.promo.movieapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.promo.movieapp.BuildConfig
import uz.promo.movieapp.R
import uz.promo.movieapp.data.remote.models.Result
import uz.promo.movieapp.data.remote.models.createTestResult
import uz.promo.movieapp.ui.theme.LocalCustomColors
import uz.promo.movieapp.ui.theme.MovieAppTheme
import uz.promo.movieapp.utils.shimmerBrush


@Composable
fun CelebrityItem(
    modifier: Modifier = Modifier,
    result: Result,
    celebrityItemClicked: (Int) -> Unit,
    paddingValues: PaddingValues = PaddingValues(
        start = 16.dp,
        top = 12.dp,
        end = 16.dp
    ),
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .height(140.dp)
        .padding(paddingValues),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = if (result.gender == 1)
                Color.Green
            else
                Color.Blue
        ),
        onClick = {
            celebrityItemClicked.invoke(result.id)
        }) {
        CelebItem(result)
    }

}

@Composable
fun CelebItem(
    result: Result
) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        val url = "${BuildConfig.IMAGE_URL}${result.profile_path}"
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Loaded Image",
            modifier = Modifier
                .fillMaxHeight()
                .width(140.dp)
                .background(LocalCustomColors.current.imageBackgroundColor)
                .background(shimmerBrush(targetValue = 1300f, showShimmer = isLoading)),
            onSuccess = { isLoading = false },
            onError = { isLoading = false },
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End
        ) {
            Text(text = result.known_for_department)
            Text(text = result.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = if (result.isLiked) R.drawable.heart_filled else R.drawable.heart_unfilled),
                contentDescription = "Like",
                tint = Color.White
            )
        }
    }

}



@Composable
fun CelebItemDetails(
    result: Result
) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val url = "${BuildConfig.IMAGE_URL}${result.profile_path}"
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Loaded Image",
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .background(LocalCustomColors.current.imageBackgroundColor)
                .background(shimmerBrush(targetValue = 1300f, showShimmer = isLoading)),
            onSuccess = { isLoading = false },
            onError = { isLoading = false },
        )


        Column(
            modifier = Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
            ) {
            Text(text = result.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            Text(text = "Known for ${result.known_for[0].name}")
            Text(text = "Release Date ${result.known_for[0].release_date}")
        }
    }

}

@Preview
@Composable
fun CelebrityItemPreview() {

    MovieAppTheme {
        CelebrityItem(
            result = createTestResult(),
            celebrityItemClicked = {}
        )
    }
}