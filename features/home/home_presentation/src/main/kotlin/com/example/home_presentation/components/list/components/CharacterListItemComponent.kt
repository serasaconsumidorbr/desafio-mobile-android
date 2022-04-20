package com.example.home_presentation.components.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_presentation.components.commons.*
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListItemComponent(
    characterHomeUiModel: CharacterHomeUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardHeight: Dp = 64.dp,
) = CharacterCardComponent(
    modifier = modifier,
    onClick = onClick,
    cardHeight = cardHeight
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
        ) {
            CharacterImageComponent(
                imageUrl = characterHomeUiModel.imageUrl,
                name = characterHomeUiModel.name,
            )
        }
        CharacterCardDividerComponent(
            modifier = Modifier
                .fillMaxHeight()
                .width(LocalSpacing.current.extraSmall)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.small)
        ) {
            CharacterNameItemComponent(
                name = characterHomeUiModel.name,
                fontSize = 14.sp
            )
            VerticalSpacer(spacing.small)
            CharacterDescriptionItemComponent(
                description = characterHomeUiModel.description,
                maxLines = 2
            )
        }
    }
}
