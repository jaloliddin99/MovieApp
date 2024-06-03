package uz.promo.movieapp.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun FreeLoading(
    isFeedLoading: Boolean = true,
    paddingTop: Dp = 8.dp
) {
    AnimatedVisibility(
        visible = isFeedLoading,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
        ) + fadeOut(),
    ) {
        val loadingContentDescription = "Description"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingTop),
        ) {
            AppLoadingWheel(
                modifier = Modifier
                    .align(Alignment.Center),
                contentDesc = loadingContentDescription,
            )
        }
    }
}

