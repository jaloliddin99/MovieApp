package uz.promo.movieapp

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import uz.promo.movieapp.ui.theme.MovieAppTheme


/**
 * this App bar is globally used for all the screens
 * with except from some screens, e.g. Details Screen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navigationIcon: ImageVector?,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },

        actions = {
            actionIcon?.let {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = actionIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier.testTag("niaTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBarPreview() {
    MovieAppTheme {
        TopAppBar(
            title = "Home Page",
            navigationIconContentDescription = "",
            navigationIcon = Icons.Filled.ArrowBack,
            actionIconContentDescription = "",
            actionIcon = Icons.Filled.Settings
        )
    }
}