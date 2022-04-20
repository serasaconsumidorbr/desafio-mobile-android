package com.example.home_presentation.carousel

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.ui.components.spacers.HorizontalSpacer

@Composable
fun HomeCarouselComponent(
    characters: List<CharacterHomeUiModel>,
) = LazyRow {
    items(characters) {
        Card {
            Text(text = it.name)
        }
        HorizontalSpacer()
    }
}