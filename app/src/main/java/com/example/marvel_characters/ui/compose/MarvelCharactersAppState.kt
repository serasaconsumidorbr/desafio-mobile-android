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
import androidx.navigation.compose.rememberNavController
import com.example.marvel_characters.network.isInternetAvailable

const val CHARACTER_DETAIL_ARG_KEY = "characterId"
sealed class Screen(val route: String) {
    object CharacterList : Screen("characterList")
    object CharacterDetail : Screen("character/{$CHARACTER_DETAIL_ARG_KEY}") {
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
    var isOnline by mutableStateOf( isInternetAvailable(context))
        private set

    fun refreshOnline() {
        isOnline =  isInternetAvailable(context)
    }

    fun navigateToCharacter(characterId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.CharacterDetail.createRoute(characterId))
        }
    }

    fun navigateBack( from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.popBackStack()

        }
    }

}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
