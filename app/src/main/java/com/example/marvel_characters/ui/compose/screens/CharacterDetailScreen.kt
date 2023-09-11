package com.example.marvel_characters.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.R
import com.example.marvel_characters.ui.compose.components.FullScreenCenteredProgress
import com.example.marvel_characters.ui.compose.components.MarvelCharacterDetailContent
import com.example.marvel_characters.ui.viewmodels.CharacterDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = getViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by characterDetailViewModel.uiState.collectAsStateWithLifecycle()
    uiState.apply {
        if (loading) {
            FullScreenCenteredProgress()
        } else if (!hadAnError()) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                MarvelCharacterDetailContent(
                    character = character!!,
                    isCharacterSaved =  isCharacterSaved,
                    onBackPressed = onBackPressed,
                    onFavoritePressed = characterDetailViewModel::onFavoritePressed
                )
            }
        } else {
            DataErrorDialog(onBackPressed)
        }
    }
}

@Composable
fun DataErrorDialog(onBackPressed: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.error)) },
        text = { Text(text = stringResource(R.string.an_error_occurred_when_trying_to_get_data)) },

        confirmButton = {
            TextButton(onClick = onBackPressed) {
                Text(stringResource(R.string.back))
            }
        }
    )
}
