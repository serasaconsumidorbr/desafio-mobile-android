package com.example.home_presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.home_presentation.components.CharacterListItemComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.LocalSpacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeListScreen(
    viewModel: HomeListViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val lazyCharacters = viewModel.getCharacters().collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            VerticalSpacer()
        }
        items(lazyCharacters) { character ->
            character?.let {
                Box(modifier = Modifier.padding(horizontal = spacing.medium)) {
                    CharacterListItemComponent(
                        character = it,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                VerticalSpacer()
            }
        }
    }
}