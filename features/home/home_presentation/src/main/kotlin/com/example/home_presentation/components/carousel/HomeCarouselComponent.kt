package com.example.home_presentation.components.carousel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_presentation.components.carousel.components.CharacterCarouselCardComponent
import com.example.ui.components.spacers.HorizontalSpacer

@Composable
fun HomeCarouselComponent(
    contentPadding: Dp,
    characters: List<CharacterHomeUiModel>,
    openCharacterDetails: () -> Unit,
    modifier: Modifier = Modifier,
    cardWidth: Int = 128,
) = LazyRow(
    modifier = modifier
        .fillMaxWidth()
        .height((2 * cardWidth).dp),
    contentPadding = PaddingValues(horizontal = contentPadding)
) {
    items(characters) { character ->
        CharacterCarouselCardComponent(
            modifier = Modifier
                .width(cardWidth.dp)
                .fillMaxHeight(),
            openCharacterDetails = openCharacterDetails,
            character = character
        )
        HorizontalSpacer()
    }
}
