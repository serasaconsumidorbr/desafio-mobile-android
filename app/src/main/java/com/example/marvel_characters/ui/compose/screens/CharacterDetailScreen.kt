package com.example.marvel_characters.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.R
import com.example.marvel_characters.ui.compose.components.MarvelCharacterDetailItem
import com.example.marvel_characters.ui.compose.viewmodels.CharacterDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = getViewModel(),
    onBackPress: () -> Unit
) {
    val uiState by characterDetailViewModel.uiState.collectAsStateWithLifecycle()

    uiState.apply {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            NewDetailAppBar {
                onBackPress()
            }

            if (loading) {

            } else if (marvelCharacter != null) {
                MarvelCharacterDetailItem(marvelCharacter = marvelCharacter)
            }
        }

    }
}

@Composable
fun NewDetailAppBar(
    onBackPressed: () -> Unit
) {



    Row(Modifier.fillMaxWidth()) {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_button)
            )
    }

    }
}