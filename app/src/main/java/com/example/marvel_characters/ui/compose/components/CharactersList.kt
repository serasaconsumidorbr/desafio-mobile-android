package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel_characters.Constants
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import com.example.marvel_characters.ui.viewmodels.CharactersUIState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map


@Composable
fun MarvelCharactersList(
    modifier: Modifier = Modifier,
    uiState: CharactersUIState,
    navigateToCharacter: (String) -> Unit,
    fetchNextCharactersFromWeb: () -> Unit,
) {

    val smallPadding = dimensionResource(id = R.dimen.small_padding)

    uiState.apply {
        val listState = rememberLazyListState()

        LazyColumn(
            modifier = modifier,
            state = listState,
            verticalArrangement = Arrangement.spacedBy(smallPadding),
            ) {

            val charactersToDisplayOnPagerQuantity =
                getCharactersToDisplayOnPagerQuantity(characters)

            val charactersToDisplayInPager =
                characters.take(charactersToDisplayOnPagerQuantity)
            val charactersToDisplayOnVerticalList =
                characters.drop(charactersToDisplayOnPagerQuantity)

            item {
                MarvelCharacterPager(
                    characters = charactersToDisplayInPager,
                    navigateToCharacter = navigateToCharacter
                )
            }

            if (charactersToDisplayOnVerticalList.isNotEmpty()) {
                items(items = charactersToDisplayOnVerticalList, key = { marvelCharacter ->
                    marvelCharacter.id
                }) { marvelCharacter ->
                    MarvelCharacterListItem(
                        Modifier.padding(horizontal = smallPadding),
                        character = marvelCharacter,
                        navigateToCharacter = navigateToCharacter
                    )
                }
            }

            val progressIndicatorShouldBeVisible = canRequestNewCharactersPage || loading
            if (progressIndicatorShouldBeVisible) {
                item(key = "circular_progress_indicator") {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = smallPadding),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(Modifier.wrapContentSize())
                    }
                }
            }
        }

        if (canRequestNewCharactersPage) {
            LaunchedEffect(loading) {
                snapshotFlow { listState.layoutInfo }.map { layoutInfo ->
                    uiIsShowingLastPageBeforeTheBottom(
                        layoutInfo
                    )
                }.distinctUntilChanged().filter { it && !loading }.collect {
                    fetchNextCharactersFromWeb()
                }
            }
        }
    }
}


private fun uiIsShowingLastPageBeforeTheBottom(layoutInfo: LazyListLayoutInfo) =
    layoutInfo.visibleItemsInfo.first().index >= layoutInfo.totalItemsCount - layoutInfo.visibleItemsInfo.size


private fun getCharactersToDisplayOnPagerQuantity(characters: List<Character>) =
    if (characters.size >= Constants.PAGER_PAGE_COUNT) {
        Constants.PAGER_PAGE_COUNT
    } else {
        characters.size
    }

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Composable
fun MarvelCharacterListPreview() {

    val marvelCharacters = listOf(
        Samples.characterWithMissingImage,
        Samples.characterAIMWithCompleteData,
        Samples.characterWithMissingImage,
        Samples.characterAIMWithCompleteData,
        Samples.characterWithMissingImage,
        Samples.characterAIMWithCompleteData
    )

    val uiState = CharactersUIState(characters = marvelCharacters, hasNextPage = false)
    MarvelCharactersTheme {
        Surface {
            MarvelCharactersList(
                uiState = uiState,
                fetchNextCharactersFromWeb = { },
                navigateToCharacter = { },
            )
        }
    }
}