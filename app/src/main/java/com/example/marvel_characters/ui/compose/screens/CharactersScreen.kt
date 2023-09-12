package com.example.marvel_characters.ui.compose.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvel_characters.R
import com.example.marvel_characters.ui.compose.components.FullScreenCenteredProgress
import com.example.marvel_characters.ui.compose.components.MarvelCharacterPager
import com.example.marvel_characters.ui.compose.components.MarvelCharactersList
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import com.example.marvel_characters.ui.viewmodels.CharactersViewModel
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.getViewModel


@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel = getViewModel(),
    navigateToCharacter: (String) -> Unit,

    ) {
    val uiState by charactersViewModel.uiState.collectAsStateWithLifecycle()

    uiState.apply {
        if (hadAnError()) {
            GenericErrorDialog(charactersViewModel::fetchCharactersFromNextWebResult)
        } else if (loading && characters.isEmpty()) {
            FullScreenCenteredProgress(true)
        }
        if (characters.isNotEmpty()) {
            MarvelCharactersList(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                navigateToCharacter = navigateToCharacter,
                fetchNextCharactersFromWeb = charactersViewModel::fetchCharactersFromNextWebResult,
            )
        } else if (!loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                Text(text = stringResource(id = R.string.no_saved_character_to_display))
            }
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
            MarvelCharacterPager(
                characters = listOf(),
                navigateToCharacter = { }
            )
        }
    }
}

@Composable
fun GenericErrorDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.error)) },
        text = { Text(text = stringResource(R.string.an_error_occurred_when_trying_to_get_data)) },

        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry_label))
            }
        }
    )
}

