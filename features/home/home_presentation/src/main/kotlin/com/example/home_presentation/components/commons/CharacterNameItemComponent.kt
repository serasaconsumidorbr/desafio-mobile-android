package com.example.home_presentation.components.commons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun CharacterNameItemComponent(
    name: String,
    fontSize: TextUnit
) = Text(
    text = name,
    style = TextStyle(
        fontSize = fontSize,
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    fontWeight = FontWeight.Bold
)
