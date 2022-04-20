package com.example.home_presentation.components.commons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCardComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardHeight: Dp = 64.dp,
    content: @Composable () -> Unit,
) = Card(
    modifier = modifier.height(cardHeight),
    shape = RoundedCornerShape(8.dp),
    onClick = onClick,
    content = content
)