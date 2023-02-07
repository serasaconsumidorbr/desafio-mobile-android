package com.desafio.android.ui.theme

import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    secondary = Accent,
    background = Accent
)

private val DarkColorPalette = darkColors(
    primary = Accent,
    primaryVariant = AccentVariant,
    secondary = Primary,
    background = Primary
)

@Composable
fun ApplicationTheme(
    window: Window,
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
    ) {
        window.statusBarColor = Primary.toArgb()
        window.navigationBarColor = Primary.toArgb()

        content()
    }
}