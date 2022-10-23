package com.example.home_presentation.components.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.home_presentation.R

@Composable
fun CharacterImageComponent(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier,
) = AsyncImage(
    modifier = modifier.fillMaxSize(),
    model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(),
    placeholder = painterResource(id = R.drawable.marvel_placeholder),
    contentDescription = name,
    contentScale = ContentScale.FillBounds
)