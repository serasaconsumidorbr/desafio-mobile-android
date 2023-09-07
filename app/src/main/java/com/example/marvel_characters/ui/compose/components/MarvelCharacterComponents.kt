package com.example.marvel_characters.ui.compose.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvel_characters.Constants
import com.example.marvel_characters.R


@Composable
fun MarvelCharacterImage(
    modifier: Modifier = Modifier,
    thumbnailUrl: String?,
    name: String
) {
    val placeholder = R.drawable.marvel_m
    val representativeImageOfCharacterText =
        stringResource(R.string.character_thumbnail_content_description, name)
    val isNotAvailableText =   stringResource(R.string.not_available, name)
    val contentDescription =
        if (thumbnailUrl.equals(Constants.IMAGE_NOT_AVAILABLE_URL)) {
            "$representativeImageOfCharacterText $isNotAvailableText"
        } else {
            representativeImageOfCharacterText
        }
    AsyncImage(

        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(thumbnailUrl)
            .crossfade(true)
            .build(),

        contentScale = ContentScale.Crop,
        error = painterResource(placeholder),
        placeholder = painterResource(placeholder),
        contentDescription = contentDescription
    )
}
@Composable
fun Name(
    modifier: Modifier = Modifier,
    name: String,
    style: TextStyle
) {
    Text(
        modifier = modifier,
        text = name, style = style, maxLines = 1,
        overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Start
    )
}

@Composable
fun Description( modifier: Modifier = Modifier, description: String, style : TextStyle) {
    val realDescription = description.ifBlank {
        stringResource(id = R.string.description_is_not_available)
    }
    Text(
        text = realDescription,
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier =  modifier,
        textAlign = TextAlign.Start
    )
}