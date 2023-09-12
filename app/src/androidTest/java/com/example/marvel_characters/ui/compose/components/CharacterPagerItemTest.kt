package com.example.marvel_characters.ui.compose.components

import androidx.activity.ComponentActivity
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples.characterAIMWithCompleteData
import com.example.marvel_characters.Samples.characterWithMissingDescription
import com.example.marvel_characters.Samples.characterWithMissingImage
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import org.junit.Rule
import org.junit.Test

class CharacterPagerItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shouldDisplayAllCharacterDataSuccessfully() {
        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPagerItem(characterAIMWithCompleteData)
                }
            }
        }

        val activity = composeTestRule.activity

        val thumbnailContentDescription =
            getThumbnailContentDescription(activity, characterAIMWithCompleteData.name)

        composeTestRule.onNodeWithText(characterAIMWithCompleteData.name)
            .assertExists("Character name was not found")

        composeTestRule.onNodeWithText(characterAIMWithCompleteData.description)
            .assertExists("Character description was not found")

        composeTestRule.onNodeWithContentDescription(thumbnailContentDescription).assertExists()
    }

    @Test
    fun shouldDisplayCharacterWithMissingImageContentDescription() {
        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPagerItem(characterWithMissingImage)
                }
            }
        }

        val activity = composeTestRule.activity

        val imageNotAvailableText =
            getImageNotAvailableText(activity, characterWithMissingImage.name)

        composeTestRule.onNodeWithContentDescription(imageNotAvailableText).assertExists()
    }

    @Test
    fun shouldDisplayCharacterWithMissingDescription() {
        composeTestRule.setContent {
            MarvelCharactersTheme {
                Surface {
                    MarvelCharacterPagerItem(characterWithMissingDescription)
                }
            }
        }

        val activity = composeTestRule.activity

        val descriptionNotAvailableTest = getDescriptionNotAvailableText(activity)

        composeTestRule.onNodeWithText(descriptionNotAvailableTest).assertExists()
    }


    private fun getImageNotAvailableText(
        activity: ComponentActivity,
        characterName: String
    ): String {
        val thumbnailContentDescription =
            getThumbnailContentDescription(activity, characterName)
        val notAvailableText = activity.getString(R.string.not_available)

        return "$thumbnailContentDescription $notAvailableText"
    }

    private fun getDescriptionNotAvailableText(
        activity: ComponentActivity
    ) = activity.getString(
        R.string.description_not_available
    )
}

private fun getThumbnailContentDescription(
    activity: ComponentActivity,
    characterName: String
) = activity.getString(
    R.string.character_thumbnail_content_description,
    characterName
)
