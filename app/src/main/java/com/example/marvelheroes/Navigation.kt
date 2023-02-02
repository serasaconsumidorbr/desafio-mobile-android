package com.example.marvelheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelheroes.ui.theme.MarvelHeroesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Navigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "heroes_list_screen"
                ) {
                    composable(route = "heroes_list_screen") {}
                    composable(
                        route = "heroes_detail_screen/{dominantColor}/{heroName}",
                        arguments = listOf(
                            navArgument(name = "dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument(name = "heroName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        val heroName = remember {
                            it.arguments?.getString("heroName")
                        }
                    }
                }
            }
        }
    }
}
