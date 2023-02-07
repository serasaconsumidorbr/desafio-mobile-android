package com.desafio.android.core.base.navigation

import android.os.Bundle
import android.os.Handler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import com.desafio.android.R
import com.desafio.android.core.base.routing.Router.addRoutes
import com.desafio.android.core.standalone.getActivity
import com.desafio.android.core.standalone.getApplicationContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class Navigator {
    private var navController: NavController? = null
    private var isNavigating by mutableStateOf(false)
    private val duration = getApplicationContext().resources.getInteger(R.integer.animation_slide_duration_long)
    private var handler: Handler = Handler()

    @ExperimentalAnimationApi
    @Composable
    fun initialize(destination: String) {
        val navController = rememberAnimatedNavController()
        this.navController = navController
        val interactionSource = remember { MutableInteractionSource() }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedNavHost(navController = navController, startDestination = destination) {
                addRoutes()
            }

            if (isNavigating) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {}
                )
            }
        }
    }

    fun navigateTo(
        route: String,
        arguments: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: androidx.navigation.Navigator.Extras? = null
    ) {
        if (isNavigating && route == navController?.currentBackStackEntry?.destination?.route) return
        isNavigating = true
        handler.removeCallbacksAndMessages(null)

        getActivity()?.runOnUiThread {
            val routeLink = NavDeepLinkRequest
                .Builder
                .fromUri(NavDestination.createRoute(route).toUri())
                .build()

            val deepLinkMatch = navController?.graph?.matchDeepLink(routeLink)

            if (deepLinkMatch != null) {
                val destination = deepLinkMatch.destination
                val id = destination.id

                navController?.navigate(id, arguments, navOptions, navigatorExtras)
            } else {
                navController?.navigate(route, navOptions, navigatorExtras)
            }

            handler.postDelayed({
                this.isNavigating = false
            }, duration.toLong())
        }
    }

    private fun getEntries(): List<NavDestination> {
        navController?.backQueue?.let { backstack ->
            return backstack.filter { it != backstack.first() }.map { it.destination }
        }

        return emptyList()
    }

    fun onBackPressed() {
        val entries = getEntries().count()

        getActivity()?.runOnUiThread {
            if (entries == 1) {
                getActivity()?.run {
                    this.moveTaskToBack(true)
                }

            } else {
                navController?.popBackStack()
            }
        }
    }
}