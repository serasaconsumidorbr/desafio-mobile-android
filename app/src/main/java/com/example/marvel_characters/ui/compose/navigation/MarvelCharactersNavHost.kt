package com.example.marvel_characters.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.marvel_characters.ui.compose.CHARACTER_LIST_ARG_KEY
import com.example.marvel_characters.ui.compose.MarvelCharactersAppState
import com.example.marvel_characters.ui.compose.Screen
import com.example.marvel_characters.ui.compose.screens.CharacterDetailScreen
import com.example.marvel_characters.ui.compose.screens.CharactersScreen
import com.example.marvel_characters.ui.compose.viewmodels.MarvelCharactersViewModel

@Composable
fun MarvelCharactersNavHost(appState: MarvelCharactersAppState) {
    NavHost(
        navController = appState.navController,
        startDestination = "characterList/${appState.isOnOfflineMode}") {

        composable(route = "characterList/${appState.isOnOfflineMode}", arguments = listOf(
            navArgument(CHARACTER_LIST_ARG_KEY){
                type = NavType.BoolType; defaultValue = appState.isOnOfflineMode
            }
        )) { backStackEntry ->

            CharactersScreen(
                navigateToCharacter = { characterId ->
                    appState.navigateToCharacterDetail(characterId, backStackEntry)
                }
            )
        }
        composable(route = Screen.CharacterDetail.route) {backStackEntry->
            CharacterDetailScreen(
                onBackPressed ={ appState.navigateBack(backStackEntry)}
            )
        }
    }
}
