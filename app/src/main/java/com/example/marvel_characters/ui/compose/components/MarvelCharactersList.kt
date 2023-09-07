package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvel_characters.Constants
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme


@Composable
fun MarvelCharactersList(
    modifier: Modifier = Modifier,
    marvelCharacters: List<MarvelCharacter>
) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(smallPadding),
       contentPadding = PaddingValues(horizontal = smallPadding)

    ) {

        val charactersToDisplayOnPagerQuantity =
            if (marvelCharacters.size >= Constants.PAGER_PAGE_COUNT) {
                Constants.PAGER_PAGE_COUNT
            } else {
                marvelCharacters.size
            }

        val charactersToDisplayInPager =
            marvelCharacters.take(charactersToDisplayOnPagerQuantity)
        val charactersToDisplayOnVerticalList =
            marvelCharacters.drop(charactersToDisplayOnPagerQuantity)


        item {
            MarvelCharacterPager(marvelCharacters = charactersToDisplayInPager)
        }
        items(
            items = charactersToDisplayOnVerticalList,
            key = { marvelCharacter ->
                marvelCharacter.id
            }
        ) { marvelCharacter ->

            MarvelCharacterListItem(marvelCharacter = marvelCharacter)

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Composable
fun MarvelCharacterListPreview() {

    MarvelCharactersTheme {
        Surface {
            MarvelCharactersList(
                marvelCharacters = listOf(
                    Samples.marvelCharacterFirst,
                    Samples.marvelCharacterSecond,
                    Samples.marvelCharacterFirst,
                    Samples.marvelCharacterSecond,
                    Samples.marvelCharacterFirst,
                    Samples.marvelCharacterSecond
                )
            )
        }
    }
}