package com.example.home_presentation.components.commons

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.LocalSpacing

@Composable
fun CharacterCardDividerComponent(modifier: Modifier = Modifier) = Divider(
    modifier = modifier,
    color = Color.Red
)