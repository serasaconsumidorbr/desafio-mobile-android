package com.example.marvel_characters.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.R
import com.example.marvel_characters.ui.compose.components.FullScreenCenteredProgressIndicator
import com.example.marvel_characters.ui.compose.components.MarvelCharacterDetailContent
import com.example.marvel_characters.ui.compose.viewmodels.CharacterDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel = getViewModel(),
    onBackPress: () -> Unit
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

                MarvelCharacterDetailContent(marvelCharacter = marvelCharacter!!, onBackPress = onBackPress)
            }
        }
    }
}

