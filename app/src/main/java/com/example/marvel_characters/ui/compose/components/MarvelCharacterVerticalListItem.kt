package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme


@Composable
fun MarvelCharacterListItem(marvelCharacter: MarvelCharacter, modifier: Modifier = Modifier) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)
    val mediumPadding = dimensionResource(id = R.dimen.medium_padding)

    ConstraintLayout(modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))) {
        val (image, name) = createRefs()
        marvelCharacter.let {

            MarvelCharacterImage(name = marvelCharacter.name,
                thumbnailUrl = marvelCharacter.thumbnailUrl,
                modifier = Modifier
                    .padding(start = smallPadding, end = mediumPadding, top = smallPadding, bottom = smallPadding).clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.list_item_thumbnail_size))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Name(name = it.name, style =  MaterialTheme.typography.titleMedium, modifier=  modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints

                })

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)

@Composable
fun MarvelCharacterListItemPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterListItem(Samples.characterWithMissingImage)
        }
    }
}




