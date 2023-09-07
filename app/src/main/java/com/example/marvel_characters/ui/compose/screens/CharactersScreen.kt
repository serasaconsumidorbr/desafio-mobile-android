package com.example.marvel_characters.ui.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.Constants.PAGER_PAGE_COUNT
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.components.MarvelCharacterPager
import com.example.marvel_characters.ui.compose.components.MarvelCharactersList
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import com.example.marvel_characters.ui.compose.viewmodels.MarvelCharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(marvelCharactersViewModel: MarvelCharactersViewModel = getViewModel()) {
    val uiState by marvelCharactersViewModel.uiState.collectAsStateWithLifecycle()

    uiState.marvelCharacters.let {
        if (it.isNotEmpty()) {
            MarvelCharactersList(marvelCharacters=it)

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