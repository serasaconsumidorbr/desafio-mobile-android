package com.example.home_presentation.components.commons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CharacterDescriptionItemComponent(
    description: String,
    maxLines: Int,
    fontSize: TextUnit = 12.sp,
) = Text(
    text = description,
    style = TextStyle(
        fontSize = fontSize,
    ),
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    fontWeight = FontWeight.Light,
    color = Color.Gray
)
