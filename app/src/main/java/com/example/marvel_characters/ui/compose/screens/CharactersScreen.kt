package com.example.marvel_characters.ui.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.ui.compose.components.MarvelCharacterPager
import com.example.marvel_characters.ui.compose.components.MarvelCharactersList
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import com.example.marvel_characters.ui.compose.viewmodels.MarvelCharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(marvelCharactersViewModel: MarvelCharactersViewModel = getViewModel()) {
    val uiState by marvelCharactersViewModel.uiState.collectAsStateWithLifecycle()

    uiState.apply {
        if (!marvelCharacters.isNullOrEmpty()) {
            MarvelCharactersList(
                marvelCharacters = uiState.marvelCharacters,
                isLoading = uiState.loading
            )
        } else if (loading){
            CircularProgressIndicator(Modifier.wrapContentSize())

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Composable
fun CharactersScreenPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterPager(marvelCharacters = listOf())
        }
    }
}