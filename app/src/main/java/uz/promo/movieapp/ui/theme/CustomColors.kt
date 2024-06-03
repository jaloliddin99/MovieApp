package uz.promo.movieapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color




internal val ImageBgColor = Color(225, 225, 225, 255)
val LocalCustomColors = compositionLocalOf { CustomColors() }

class CustomColors(val imageBackgroundColor: Color = ImageBgColor)



