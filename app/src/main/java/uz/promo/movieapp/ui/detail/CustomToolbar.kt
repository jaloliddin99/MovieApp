package uz.promo.movieapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.promo.movieapp.ui.theme.MovieAppTheme


@Composable
fun DetailsToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
            .height(56.dp)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = modifier.weight(1f))


    }
}

@Composable
fun TopShadow(
    modifier: Modifier = Modifier
) {
    val black = Color.Black.copy(alpha = 0.5f)
    val transparentBlack = Color.Black.copy(alpha = 0f)

    val gradientColors = listOf(black, transparentBlack)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                brush = Brush.verticalGradient(gradientColors),
            ),

        ) {

    }
}

@Preview
@Composable
fun DetailsToolbarPreview() {
    MovieAppTheme {
        DetailsToolbar {

        }
    }
}

@Preview
@Composable
fun TopShadowPreview() {
    MovieAppTheme {
        TopShadow()
    }
}