package com.counter.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    background = white,
    surface = white,
    primary = blue,
    primaryVariant = black,
    secondary = purple500,
    onPrimary = white,
    onSecondary = white
)

private val DarkColorPalette = darkColors(
    background = black,
    onBackground = background,
    primary = white,
    primaryVariant = white,
    secondary = purple500,
    onPrimary = white,
    onSecondary = white
)

@Composable
fun CounterTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}