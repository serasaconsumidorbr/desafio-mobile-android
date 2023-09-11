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
import androidx.compose.ui.unit.dp
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme

@Composable
fun MarvelCharacterPagerItem(character: Character, modifier: Modifier = Modifier) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)

    character.apply {
        Column(
            modifier = modifier.padding(smallPadding)
        ) {
            CharacterImage(
                name = character.name,
                thumbnailUrl = character.thumbnailUrl,
                modifier = Modifier
                    .padding(bottom = smallPadding)
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.large_character_thumbnail_height))

            )
            Name( modifier = Modifier.padding(vertical = smallPadding), name = name, MaterialTheme.typography.titleLarge,)
            DescriptionWithLinesLimit(modifier.height(36.dp), description = description, MaterialTheme.typography.labelLarge)
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
            MarvelCharacterPagerItem(Samples.characterWithMissingImage)
        }
    }
}


