package com.marvelverse.ui

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import com.marvelverse.R
import com.marvelverse.data.Data
import com.marvelverse.data.MarvelAPIResponseDTO
import com.marvelverse.data.ResultDTO
import com.marvelverse.data.Thumbnail
import com.marvelverse.data.datasources.FavoritesDbDataSource
import com.marvelverse.data.datasources.RemoteDataSource
import com.marvelverse.data.db.MarvelCharactersFavoriteDatabase
import com.marvelverse.data.network.MarvelAPI
import com.marvelverse.data.network.PicassoThumbnailService
import com.marvelverse.data.repositories.DefaultMarvelCharactersRepository
import com.marvelverse.di.MarvelCharactersModule
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.MarvelCharactersRepository
import com.marvelverse.domain.ThumbnailImage
import com.marvelverse.domain.ThumbnailService
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(MarvelCharactersModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainViewShould {

    @BindValue
    lateinit var marvelAPI: MarvelAPI

    @BindValue
    @MockK
    lateinit var remoteDataSource: RemoteDataSource

    @BindValue
    lateinit var favoritesDbDataSource: FavoritesDbDataSource

    @BindValue
    lateinit var marvelCharactersRepository: MarvelCharactersRepository

    @BindValue
    var thumbnailService: ThumbnailService = PicassoThumbnailService()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun show_master_view_with_loaded_characters() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        val recyclerView = onView(withId(R.id.recycler_view_characters))

        recyclerView.check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun show_master_view_with_correct_details_for_loaded_characters() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)

        launchMainActivity()

        val expectedThumbnail =
            ThumbnailImage("https://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73", "jpg")
        val expectedCharacter = MarvelCharacter("Fake Hero", expectedThumbnail)
        checkCharacterCardIsLoaded(expectedCharacter)
    }

    private fun checkCharacterCardIsLoaded(marvelCharacter: MarvelCharacter) {
        val marvelCharacterName = onView(withId(R.id.text_marvel_character_name))
        marvelCharacterName.check(matches(withText(marvelCharacter.characterName)))
        val marvelCharacterImage = onView(withId(R.id.image_marvel_character_thumbnail))
        marvelCharacterImage.check(matches(withContentDescription("${marvelCharacter.characterName} thumbnail")))
    }

    @Test
    fun load_character_thumbnail() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        val characterView = onView(withText("Fake Hero"))
        characterView.perform(ViewActions.click())

        val marvelCharacterImage = onView(withId(R.id.image_marvel_character_details_thumbnail))
        marvelCharacterImage.check(matches(withContentDescription("Fake Hero thumbnail")))
    }

    @Test
    fun load_character_description() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        val characterView = onView(withText("Fake Hero"))
        characterView.perform(ViewActions.click())

        val marvelCharacterDescription =
            onView(withId(R.id.text_marvel_character_details_description))
        marvelCharacterDescription.check(matches(withText("This is a Fake marvel character")))
    }

    @Test
    fun load_character_without_any_available_description() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTOWithEmptyDescription
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        val characterView = onView(withText("Fake Hero"))
        characterView.perform(ViewActions.click())

        val marvelCharacterDescription =
            onView(withId(R.id.text_marvel_character_details_description))
        marvelCharacterDescription.check(matches(withText("N/A")))
    }

    @Test
    fun show_error_page_when_api_fails() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } throws Exception()
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        val errorPage = onView(withId(R.id.error_page_layout))
        errorPage.check(matches(isDisplayed()))
    }

    @Test
    fun show_empty_character_list_when_there_are_not_favorite_characters() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        navigateToFavorites()

        val recyclerView = onView(withId(R.id.recycler_view_favorite_characters))
        recyclerView.check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun show_a_favorite_character_when_it_is_selected_as_favorite() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        selectFakeHeroAsFavorite()
        navigateToFavorites()

        val recyclerView = onView(withId(R.id.recycler_view_favorite_characters))
        recyclerView.check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun show_favorite_character_details_when_it_is_selected_as_favorite() {
        coEvery {
            remoteDataSource.getCharacters(any(), any(), any())
        } returns fakeMarvelAPIResponseDTO
        initMarvelCharactersRepositoryWith(remoteDataSource)
        launchMainActivity()

        selectFakeHeroAsFavorite()
        navigateToFavorites()
        navigateToFakeHeroDetails()

        val fakeHeroDescription =
            onView(withId(R.id.text_marvel_character_details_description))
        fakeHeroDescription.check(matches(withText("This is a Fake marvel character")))
    }

    private fun navigateToFakeHeroDetails() {
        val characterView = onView(withText("Fake Hero"))
        characterView.perform(ViewActions.click())
    }

    private fun navigateToFavorites() {
        val buttonNavFavorites = onView(withId(R.id.favorites_fragment))
        buttonNavFavorites.perform(ViewActions.click())
    }

    private fun selectFakeHeroAsFavorite() {
        val characterView = onView(withText("Fake Hero"))
        characterView.perform(ViewActions.click())

        val favButton = onView(withId(R.id.fab_toggle_favorites))
        favButton.perform(ViewActions.click())
    }

    private fun initMarvelCharactersRepositoryWith(remoteDataSource: RemoteDataSource) {
        favoritesDbDataSource = setupFavoritesDBDatasource()
        marvelCharactersRepository = DefaultMarvelCharactersRepository(
            remoteDataSource,
            favoritesDbDataSource,
            MarvelAPI(apiKey = "", privateKey = "")
        )
    }

    private fun setupFavoritesDBDatasource(): FavoritesDbDataSource {
        val database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelCharactersFavoriteDatabase::class.java).build()
        return FavoritesDbDataSource(database.marvelCharacterFavoritesDao())
    }

    private var fakeMarvelAPIResponseDTO = MarvelAPIResponseDTO(
        "200",
        Data(
            "1",
            "1",
            "1",
            listOf(ResultDTO(
                "This is a Fake marvel character",
                "1",
                "a modified date",
                "Fake Hero",
                Thumbnail(
                    "jpg",
                    "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73"
                )
            )),
            "1000"
        ),
        "Ok")

    private var fakeMarvelAPIResponseDTOWithEmptyDescription = MarvelAPIResponseDTO(
        "200",
        Data(
            "1",
            "1",
            "1",
            listOf(ResultDTO(
                "",
                "1",
                "a modified date",
                "Fake Hero",
                Thumbnail(
                    "jpg",
                    "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73"
                )
            )),
            "1000"
        ),
        "Ok")

    private fun launchMainActivity() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        scenario = ActivityScenario.launch(intent)
    }
}

class RecyclerViewItemCountAssertion(expectedCount: Int) : ViewAssertion {
    private val matcher: Matcher<Int?>? = CoreMatchers.`is`(expectedCount)

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView?
        val adapter = recyclerView!!.adapter!!
        assertThat(adapter.itemCount, matcher)
    }
}