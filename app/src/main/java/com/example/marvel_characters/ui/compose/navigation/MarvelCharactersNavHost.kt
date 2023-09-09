package com.example.marvel_characters.ui.compose.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
        startDestination = Screen.CharacterList.route) {

        composable(Screen.CharacterList.route) { backStackEntry ->
            CharactersScreen(
                navigateToCharacter = { characterId ->
                    appState.navigateToCharacter(characterId, backStackEntry)
                }
            )
        }
        composable(route = Screen.CharacterDetail.route) {
            CharacterDetailScreen(
                onBackPress = appState::navigateBack
            )
        }
    }
}
