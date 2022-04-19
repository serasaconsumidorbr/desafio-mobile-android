package com.example.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color.Red,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun MarvelCharactersTheme(
    content: @Composable () -> Unit,
) = CompositionLocalProvider(LocalSpacing provides Dimensions()) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}