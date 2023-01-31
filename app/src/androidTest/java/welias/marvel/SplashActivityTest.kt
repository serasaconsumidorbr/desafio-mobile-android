package welias.marvel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import welias.marvel.presentation.ui.activity.splash.SplashActivity

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun shouldLogoIsVisible() {
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
    }
}
