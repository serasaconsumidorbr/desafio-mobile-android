package com.desafio.android.core.base.routing

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.desafio.android.R
import com.desafio.android.core.standalone.getApplicationContext
import com.desafio.android.ui.screen.home.addHomeRoutes
import com.google.accompanist.navigation.animation.composable

object Router {
    @ExperimentalAnimationApi
    fun NavGraphBuilder.addRoutes() {
        addHomeRoutes()
    }

    @ExperimentalAnimationApi
    fun NavGraphBuilder.addSlideRoute(
        route: String,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        val duration =
            getApplicationContext().resources.getInteger(R.integer.animation_slide_duration_short)

        composable(
            route = route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(duration)
                )
            },
            popEnterTransition = {
                null
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(duration)
                )
            },
            content = content
        )
    }

    @ExperimentalAnimationApi
    fun NavGraphBuilder.addFadeRoute(
        route: String,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        val duration =
            getApplicationContext().resources.getInteger(R.integer.animation_fade_duration_regular)

        composable(
            route = route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(duration)
                )
            },
            popEnterTransition = {
                null
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(duration)
                )
            },
            content = content
        )
    }
}