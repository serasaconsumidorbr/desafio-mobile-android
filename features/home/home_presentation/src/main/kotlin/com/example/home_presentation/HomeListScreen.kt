package com.example.home_presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun HomeListScreen(
    viewModel: HomeListViewModel = hiltViewModel(),
) {
    val lazyCharacters = viewModel.getCharacters().collectAsLazyPagingItems()
    LazyColumn {
        items(lazyCharacters) { character ->
            character?.let {
                Text(text = character.name)
            }
        }
    }
}