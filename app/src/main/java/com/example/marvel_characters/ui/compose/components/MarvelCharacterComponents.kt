package com.example.marvel_characters.ui.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvel_characters.R
import com.example.marvel_characters.domain.MarvelCharacter


@Composable
fun MarvelCharacterImage(modifier: Modifier = Modifier, thumbnailUrl: String?) {
    val placeholder = R.drawable.marvel_m

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(thumbnailUrl)
            .crossfade(true)
            .build(),

        contentScale = ContentScale.Crop,
        error = painterResource(placeholder),
        placeholder = painterResource(placeholder),
        contentDescription = stringResource(R.string.character_thumbnail_content_description),
        modifier = modifier
            .fillMaxWidth()
            .height( dimensionResource(id = R.dimen.thumbnail_height))
    )
}

@Composable
fun MarvelCharacter.Description(modifier: Modifier = Modifier) {
    val style = MaterialTheme.typography.labelMedium

    val realDescription = description.ifBlank {
        stringResource(id = R.string.description_not_available)
    }
    Text(
        text = realDescription,
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.height(48.dp), textAlign = TextAlign.Start
    )
}

@Composable
fun MarvelCharacter.Name(modifier: Modifier = Modifier) {
    Text(
        name, style = MaterialTheme.typography.titleLarge, maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
