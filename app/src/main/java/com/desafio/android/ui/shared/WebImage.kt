package com.desafio.android.ui.shared

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.desafio.android.core.standalone.getImageFromUrl
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun WebImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    progressColor: Color = Color.Black
) {
    var drawable: Drawable? by remember { mutableStateOf(null) }

    LaunchedEffect(url) {
        getImageFromUrl(
            url = url,
            onLoading = {
                drawable = it
            },
            onLoaded = {
                drawable = it
            },
            color = progressColor
        )
    }

    Image(
        modifier = modifier,
        contentScale = contentScale,
        painter = rememberDrawablePainter(
            drawable = drawable
        ),
        contentDescription = contentDescription
    )
}