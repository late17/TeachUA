package com.teachuacompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


//private val MaterialLightTheme = lightColors(
//
//    primary = md_theme_light_primary,
//    onPrimary = md_theme_light_onPrimary,
//    secondary = md_theme_light_secondary,
//    onSecondary = md_theme_light_onSecondary,
//    error = md_theme_light_error,
//    onError = md_theme_light_onError,
//    background = md_theme_light_background,
//    onBackground = md_theme_light_onBackground,
//    surface = md_theme_light_surface,
//    onSurface = md_theme_light_onSurface,
//)
//private val MaterialDarkTheme = darkColors(
//
//    primary = md_theme_dark_primary,
//    onPrimary = md_theme_dark_onPrimary,
//    secondary = md_theme_dark_secondary,
//    onSecondary = md_theme_dark_onSecondary,
//    error = md_theme_dark_error,
//    onError = md_theme_dark_onError,
//    background = md_theme_dark_background,
//    onBackground = md_theme_dark_onBackground,
//    surface = md_theme_dark_surface,
//    onSurface = md_theme_dark_onSurface,
//)

private val DarkColorPalette = darkColors(
    primary = md_theme_dark_onPrimary,
    onPrimary = Color.White,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_primaryContainer,
    onSurface = md_theme_dark_onPrimaryContainer
)

private val LightColorPalette = lightColors(
    primary = Orange500,
    onPrimary = Color.Black,
    primaryVariant = Orange700,

    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = Orange200,
    onSurface = md_theme_light_onSurface

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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