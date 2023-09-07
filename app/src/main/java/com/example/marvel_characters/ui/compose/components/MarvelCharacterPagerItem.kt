package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme

@Composable
fun MarvelCharacterPagerItem(marvelCharacter: MarvelCharacter, modifier: Modifier = Modifier) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)

    marvelCharacter.apply {
        Column(
            modifier = Modifier.padding(smallPadding)
        ) {
            MarvelCharacterImage(
                name = marvelCharacter.name,
                thumbnailUrl = marvelCharacter.thumbnailUrl,
                modifier = Modifier
                    .padding(bottom = smallPadding)
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.pager_item_thumbnail_height))

            )
            Name( modifier = Modifier.padding(vertical = smallPadding), name = name, MaterialTheme.typography.titleLarge,)
            Description(modifier.height(36.dp), description = description, MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)

@Composable
fun MarvelCharacterPagerItemPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterPagerItem(Samples.marvelCharacterFirst)
        }
    }
}


