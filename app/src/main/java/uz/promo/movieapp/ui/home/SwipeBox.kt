package uz.promo.movieapp.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uz.promo.movieapp.utils.vibratePhone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeBox(
    modifier: Modifier = Modifier,
    onDislike: () -> Unit,
    onLike: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()
    val context = LocalContext.current

    lateinit var icon: ImageVector
    lateinit var alignment: Alignment
    val color: Color = Color.Transparent

    when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {
            icon = Icons.Outlined.ThumbDown
            alignment = Alignment.CenterEnd
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            icon = Icons.Outlined.ThumbUp
            alignment = Alignment.CenterStart
        }

        SwipeToDismissBoxValue.Settled -> {
            icon = Icons.Outlined.ThumbDown
            alignment = Alignment.CenterEnd
        }
    }

    SwipeToDismissBox(
        modifier = modifier
            .background(color = Color.Transparent)
            .animateContentSize(),
        state = swipeState,
        backgroundContent = {
            Box(
                contentAlignment = alignment,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    modifier = Modifier.minimumInteractiveComponentSize(),
                    imageVector = icon, contentDescription = null
                )
            }
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            LaunchedEffect(swipeState) {
                onDislike()
                vibratePhone(context)
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            LaunchedEffect(swipeState) {
                onLike()
                vibratePhone(context)
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.Settled -> {
        }
    }
}