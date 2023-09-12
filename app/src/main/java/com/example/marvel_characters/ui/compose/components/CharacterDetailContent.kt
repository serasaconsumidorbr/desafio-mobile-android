package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme


@Composable
fun MarvelCharacterDetailContent(
    character: Character,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onFavoritePressed: () -> Unit,
    isCharacterSaved: Boolean
) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)
    val mediumPadding = dimensionResource(id = R.dimen.medium_padding)


    ConstraintLayout(
        modifier = modifier.fillMaxWidth()

    ) {
        val (buttons, image, remainingCharacterData) = createRefs()

        character.let {

            CharacterImage(
                modifier = Modifier
                    .padding(bottom = smallPadding)
                    .height(dimensionResource(id = R.dimen.large_character_thumbnail_height))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }, thumbnailUrl = it.thumbnailUrl, name = it.name
            )

            Buttons(Modifier.constrainAs(buttons) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, onBackPressed, onFavoriteClick = onFavoritePressed, isSaved = isCharacterSaved)
            Column(
                Modifier
                    .padding(
                        start = mediumPadding, end = mediumPadding, bottom = mediumPadding
                    )
                    .constrainAs(remainingCharacterData) {
                        top.linkTo(image.bottom)
                    }) {

                Name(
                    modifier = Modifier.padding(bottom = smallPadding),
                    name = it.name,
                    style = MaterialTheme.typography.titleLarge
                )
                DescriptionFull(
                    description = it.description, style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun Buttons(
    modifier: Modifier, onBackPressed: () -> Unit, onFavoriteClick: () -> Unit, isSaved: Boolean
) {

    val buttonElevation = dimensionResource(id = R.dimen.button_elevation)

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (backArrow, optionsMenu) = createRefs()

        IconButton(onClick = onBackPressed,
            Modifier
                .constrainAs(backArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {
            Icon(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(buttonElevation),
                        shape = CircleShape
                    ),

                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        val favoriteIcon: ImageVector
        val favoriteButtonContentDescription: String

        if (isSaved) {
            favoriteIcon = Icons.Filled.Favorite
            favoriteButtonContentDescription = stringResource(id = R.string.mark_as_favorite)
        } else {
            favoriteIcon = Icons.Filled.FavoriteBorder
            favoriteButtonContentDescription = stringResource(id = R.string.remove_favorite)
        }

        IconButton(onClick = { onFavoriteClick() },
            Modifier
                .constrainAs(optionsMenu) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }) {


            Icon(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(buttonElevation),
                        shape = CircleShape
                    ),

                imageVector = favoriteIcon,
                contentDescription = favoriteButtonContentDescription
            )

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)

@Composable
fun MarvelCharacterDetailItemPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterListItem(character = Samples.characterAIMWithCompleteData, navigateToCharacter = { })
        }
    }
}




