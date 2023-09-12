package com.example.marvel_characters.ui.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvel_characters.network.isInternetAvailable

const val CHARACTER_ID_ARG_KEY = "characterId"

const val START_ON_OFFILINE_MODE_ARG_KEY = "startOnOfflineMode"

sealed class Screen(val route: String) {
    object CharacterList : Screen("characterList/{$START_ON_OFFILINE_MODE_ARG_KEY}") {
        fun createRoute() = route
        //TODO: find a way to refactor this code and eliminate the needing of passing the startOnOfflineMode twice
        fun createArguments(startOnOfflineMode: Boolean) = listOf(
            navArgument(START_ON_OFFILINE_MODE_ARG_KEY){
                type = NavType.BoolType; defaultValue =startOnOfflineMode
            }
        )
    }

    object CharacterDetail : Screen("character/{$CHARACTER_ID_ARG_KEY}") {
        fun createRoute(characterId: String) = "character/$characterId"
    }
}

@Composable
fun rememberMarvelCharactersAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    MarvelCharactersAppState(navController, context)
}

class MarvelCharactersAppState(
    val navController: NavHostController,
    private val context: Context
) {
    var isOnOfflineMode by mutableStateOf(false)
    var isOnline by mutableStateOf(isInternetAvailable(context))
        private set

    fun refreshOnline() {
        isOnline = isInternetAvailable(context)
    }

    fun navigateToCharacterDetail(characterId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.CharacterDetail.createRoute(characterId))
        }
    }

    fun navigateBack(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.popBackStack()
        }
    }

    fun enterOnOfflineMode() {
        isOnOfflineMode = true
    }

}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
