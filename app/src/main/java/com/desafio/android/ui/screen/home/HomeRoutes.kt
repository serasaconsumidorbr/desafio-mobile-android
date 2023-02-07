package com.desafio.android.ui.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.desafio.android.core.base.routing.Router.addSlideRoute

object HomeRoutes {
    const val HOME_SCREEN = "HOME_SCREEN"
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addHomeRoutes() {
    addSlideRoute(HomeRoutes.HOME_SCREEN) {
        HomeScreen()
    }
}