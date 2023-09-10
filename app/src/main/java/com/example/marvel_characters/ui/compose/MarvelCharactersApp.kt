package com.example.marvel_characters.ui.compose

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvel_characters.R
import com.example.marvel_characters.ui.compose.navigation.MarvelCharactersNavHost
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme

//uiState: MarvelCharactersUIState
@Composable
fun MarvelCharactersApp(
    appState: MarvelCharactersAppState = rememberMarvelCharactersAppState()
) {
    val shouldShowAppContent by remember { derivedStateOf { appState.isOnline || appState.isOnOfflineMode } }

    if (shouldShowAppContent) {
        MarvelCharactersNavHost(appState)
    } else {
        OfflineDialog(appState::refreshOnline, appState::enterOnOfflineMode)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Composable
fun MarvelCharactersAppPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharactersApp()
        }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit, offlineMode: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = stringResource(R.string.internet_connection_error_title)) },
        text = { Text(text = stringResource(R.string.internet_connection_error_message)) },

        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry_label))
            }
        },
        dismissButton = {
            TextButton(onClick = offlineMode) {
                Text(stringResource(R.string.enterOnOfflineMode))
            }
        }
    )
}

