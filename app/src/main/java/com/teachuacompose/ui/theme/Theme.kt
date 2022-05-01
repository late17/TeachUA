package com.teachuacompose.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color




private val DarkColorPalette = darkColors(
    primary = Orange900,
    onPrimary = White,
    secondaryVariant = Blue600,

    background = Gray900,
    onBackground = White,
    surface = Orange900,
    onSurface = White
)

private val LightColorPalette = lightColors(
    primary = Orange600,
    onPrimary = Black,
    secondaryVariant = Blue600,

    background = Gray200,
    onBackground = Black,
    surface = Orange100,
    onSurface = Black
)

@Composable
fun TeachUaComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}