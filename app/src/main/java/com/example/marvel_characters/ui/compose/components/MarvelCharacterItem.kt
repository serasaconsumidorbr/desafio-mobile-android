package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun MarvelCharacterItem(marvelCharacter: MarvelCharacter, modifier: Modifier = Modifier) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)

    marvelCharacter.apply {
        Column(
            modifier = Modifier.padding(smallPadding)
        ) {
            MarvelCharacterImage(
                name = marvelCharacter.name,
                thumbnailUrl = marvelCharacter.thumbnailUrl,
                modifier = Modifier.padding(bottom = smallPadding)
            )
            Name(modifier = Modifier.padding(vertical = smallPadding))
            Description()
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)

@Composable
fun MarvelCharacterPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterItem(Samples.marvelCharacterFirst)
        }
    }
}