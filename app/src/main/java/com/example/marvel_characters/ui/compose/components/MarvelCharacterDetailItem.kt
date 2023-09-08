package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme


@Composable
fun MarvelCharacterDetailItem(
    marvelCharacter: MarvelCharacter,
    modifier: Modifier = Modifier
) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)
    val mediumPadding = dimensionResource(id = R.dimen.medium_padding)

    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {
        marvelCharacter.apply {
            CharacterImage(
                modifier = Modifier
                    .padding(bottom = smallPadding)
                    .height(dimensionResource(id = R.dimen.large_character_thumbnail_height)),
                thumbnailUrl = thumbnailUrl,
                name = name
            )

            Column(Modifier.padding(start = mediumPadding, end = mediumPadding, bottom = mediumPadding)) {

                Name(
                    modifier = Modifier.padding(bottom = smallPadding),
                    name = name,
                    style = MaterialTheme.typography.headlineMedium
                )

                DescriptionFull(
                    description = description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)

@Composable
fun MarvelCharacterDetailItemPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterListItem(
                Samples.characterWithCompleteData,
                navigateToCharacter = { }
            )
        }
    }
}




