package com.br.leandro.marvel_hero_app.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.br.leandro.marvel_hero_app.R
import com.br.leandro.marvel_hero_app.ui.core.HeroViewHolder
import com.br.leandro.marvel_hero_app.ui.home.HomeFragment
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test


class HomeHeroFragmentTest {

    private lateinit var navController: TestNavHostController
    private lateinit var homeScenario: FragmentScenario<HomeFragment>

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setViewModelStore(ViewModelStore())
        navController.setGraph(R.navigation.mobile_navigation)
        homeScenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme) {
            HomeFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
        homeScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun testNavigateFromHomeToHero() {
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.homeHeroRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HeroViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        val currentDestination = navController.backStack.last()

        assertThat(currentDestination.destination.id).isEqualTo(R.id.navigation_hero)
    }

    @Test
    fun testFavoriteFromHomeToHero() {
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.homeHeroRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HeroViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getDescription(): String {
                            return "Click on favorite button in the first element of recycler view"
                        }

                        override fun getConstraints(): Matcher<View> {
                            return mockk()
                        }

                        override fun perform(uiController: UiController?, view: View?) {
                            view?.findViewById<View>(R.id.homeHeroFavoriteCheckBox)?.performClick()
                        }
                    }
                )
            )
    }
}