package com.example.marvel_characters.ui.compose.components

import androidx.activity.ComponentActivity
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.example.marvel_characters.Constants
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.Samples.marvelCharacterSecond
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MarvelCharacterPagerTest {

    private val charactersSampleList = Samples.marvelCharactersList
    private val characterFirst = Samples.marvelCharacterFirst
    private val characterSecond = marvelCharacterSecond


    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showCharacterListElementsOnPager() {

        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPager(marvelCharacters = charactersSampleList)
                }
            }
        }

        charactersSampleList.forEachIndexed { index, character ->
            val thumbnailContentDescription =
                composeTestRule.activity.getString(
                    R.string.character_thumbnail_content_description,
                    character.name
                )

            composeTestRule.onNodeWithText(character.name)
                .assertExists("Character name was not found")

            composeTestRule.onNodeWithText(character.description)
                .assertExists("Character description was not found")

            composeTestRule.onNodeWithContentDescription(thumbnailContentDescription).assertExists()

            val maxPagerIndex = charactersSampleList.size - 1
            if (index < maxPagerIndex) {
                composeTestRule.onNodeWithTag(Constants.PAGER_TEST_TAG)
                    .performScrollToIndex(index + 1)
            }
        }
    }

    @Test
    fun couldAccessAllPagerPages() {
        val charactersSampleList =
            listOf(characterFirst, characterSecond, characterFirst, characterSecond, characterFirst)


        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPager(marvelCharacters = charactersSampleList)
                }
            }
        }

        val returnType =
            try {
                for (index in 0 until Constants.PAGER_PAGE_COUNT) {
                    composeTestRule.onNodeWithTag(Constants.PAGER_TEST_TAG)
                        .performScrollToIndex(index)
                }
            } catch (exception: Exception) {
                exception
            }

        Assert.assertTrue(returnType is Unit)
    }

    @Test
    fun couldNotAccessMorePagesThanSpecified() {
        val charactersSampleList =
            listOf(characterFirst, characterSecond, characterFirst, characterSecond, characterFirst)

        val specifiedPagesQuantity = Constants.PAGER_PAGE_COUNT

        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPager(marvelCharacters = charactersSampleList)
                }
            }
        }

        val returnType =
            try {
                for (index in 0 until specifiedPagesQuantity + 1) {
                    composeTestRule.onNodeWithTag(Constants.PAGER_TEST_TAG)
                        .performScrollToIndex(index)
                }
            } catch (exception: Exception) {
                exception
            }

        Assert.assertTrue(returnType is IllegalArgumentException)
    }
}
