package com.example.marvel_characters.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.ui.compose.components.FullScreenCenteredProgressIndicator
import com.example.marvel_characters.ui.compose.components.MarvelCharacterDetailContent
import com.example.marvel_characters.ui.compose.viewmodels.CharacterDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = getViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by characterDetailViewModel.uiState.collectAsStateWithLifecycle()

    uiState.apply {
        if (loading) {
            FullScreenCenteredProgressIndicator()
        } else if (!hadAnError()) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                MarvelCharacterDetailContent(marvelCharacter = marvelCharacter!!, onBackPressed = onBackPressed, onDownloadPressed = characterDetailViewModel::onDownloadPressed)
            }
        }
    }
}

