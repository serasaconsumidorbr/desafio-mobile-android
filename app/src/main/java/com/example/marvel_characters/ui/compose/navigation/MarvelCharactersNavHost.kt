package com.example.marvel_characters.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvel_characters.ui.compose.MarvelCharactersAppState
import com.example.marvel_characters.ui.compose.Screen
import com.example.marvel_characters.ui.compose.screens.CharacterDetailScreen
import com.example.marvel_characters.ui.compose.screens.CharactersScreen

@Composable
fun MarvelCharactersNavHost(appState: MarvelCharactersAppState) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.CharacterList.createRoute()
    ) {

        composable(
            route = Screen.CharacterList.route,
            arguments = Screen.CharacterList.createArguments(appState.isOnOfflineMode)
        ) { backStackEntry ->
            CharactersScreen(
                navigateToCharacter = { characterId ->
                    appState.navigateToCharacterDetail(characterId, backStackEntry)
                }
            )
        }
        composable(route = Screen.CharacterDetail.route) { backStackEntry ->
            CharacterDetailScreen(
                onBackPressed = { appState.navigateBack(backStackEntry) }
            )
        }
    }
}
