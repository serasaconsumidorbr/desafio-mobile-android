package com.example.home_presentation.components.carousel.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_presentation.components.commons.CharacterCardDividerComponent
import com.example.home_presentation.components.commons.CharacterDescriptionItemComponent
import com.example.home_presentation.components.commons.CharacterImageComponent
import com.example.home_presentation.components.commons.CharacterNameItemComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCarouselCardComponent(
    openCharacterDetails: () -> Unit,
    character: CharacterHomeUiModel,
    modifier: Modifier = Modifier,
) = Card(
    onClick = openCharacterDetails,
    modifier = modifier
) {
    val spacing = LocalSpacing.current
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            CharacterImageComponent(
                imageUrl = character.imageUrl,
                name = character.name
            )
        }
        CharacterCardDividerComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.extraSmall)
        )
        VerticalSpacer(spacing.small)
        Column(modifier = Modifier.padding(spacing.small)) {
            CharacterNameItemComponent(
                name = character.name,
                fontSize = 16.sp
            )
            VerticalSpacer(spacing.small)
            CharacterDescriptionItemComponent(
                description = character.description,
                maxLines = 4
            )
        }
    }
}