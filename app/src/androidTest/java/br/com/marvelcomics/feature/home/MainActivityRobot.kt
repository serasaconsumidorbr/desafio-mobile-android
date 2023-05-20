package br.com.marvelcomics.feature.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.marvelcomics.R
import br.com.marvelcomics.base.util.Resource
import br.com.marvelcomics.base.util.UiException
import br.com.marvelcomics.data.repository.MarvelCharRepository
import br.com.marvelcomics.model.MarvelCharacter
import br.com.marvelcomics.utils.atPosition
import io.mockk.coEvery
import kotlinx.coroutines.flow.flow
import org.hamcrest.Matchers.not
import org.koin.test.KoinTest
import org.koin.test.inject

fun MainActivityTest.executeTest(func: MainActivityRobot.() -> Unit) =
    MainActivityRobot().apply { func() }

class MainActivityRobot : KoinTest {


    private val repo: MarvelCharRepository by inject()

    private val mockList = listOf(
        MarvelCharacter(0L, "name1", "description1", "thumb"),
        MarvelCharacter(1L, "name2", "description2", "thumb"),
        MarvelCharacter(2L, "name3", "description3", "thumb"),
        MarvelCharacter(3L, "name4", "description4", "thumb"),
        MarvelCharacter(4L, "name5", "description5", "thumb"),
        MarvelCharacter(5L, "name6", "description6", "thumb"),
    )

    fun withFetchCharSuccess() {
        coEvery { repo.fetchCharacters(any()) } returns flow { emit(Resource.Success(mockList)) }
    }

    fun withFetchCharLoading() {
        coEvery { repo.fetchCharacters(any()) } returns flow { emit(Resource.Loading()) }
    }

    fun withFetchHeroesError(error: UiException) {
        coEvery { repo.fetchCharacters(any()) } returns flow { emit(Resource.Error(error)) }
    }

    infix fun launch(func: MainActivityRobot.() -> Unit): MainActivityRobot {
        ActivityScenario.launch(MainActivity::class.java)
        return this.apply(func)
    }

    infix fun check(func: Result.() -> Unit) = Result().apply(func)

    inner class Result {
        fun checkRecyclerViewContainsItem(position: Int,text: String) {
            onView(withId(R.id.rv_marvel_list))
                .check(matches(atPosition(position, hasDescendant(withText(text)))));
        }

        fun checkRecyclerViewTitleItem(position: Int,text: String) {
            onView(withId(R.id.rv_marvel_list))
                .check(matches(atPosition(position, withText(text))));
        }

        fun checkProgressBarMessageIsDisplayed() {
            onView(withId(R.id.progress))
                .check(matches(ViewMatchers.isDisplayed()))
        }

        fun checkProgressBarMessageIsNotDisplayed() {
            onView(withId(R.id.progress))
                .check(matches(not(ViewMatchers.isDisplayed())))
        }

        fun checkErrorMessage(message: String) {
            onView(withId(R.id.error_message))
                .check(matches(withText(message)))
        }

        fun checkErrorMessageIsDisplayed() {
            onView(withId(R.id.error_message))
                .check(matches(ViewMatchers.isDisplayed()))
        }

        fun checkErrorMessageIsNotDisplayed() {
            onView(withId(R.id.error_message))
                .check(matches(not(ViewMatchers.isDisplayed())))
        }

        fun checkErrorButtonIsDisplayed() {
            onView(withId(R.id.btn_try_again))
                .check(matches(ViewMatchers.isDisplayed()))
        }

        fun checkErrorButtonIsNotDisplayed() {
            onView(withId(R.id.btn_try_again))
                .check(matches(not(ViewMatchers.isDisplayed())))
        }
    }

}