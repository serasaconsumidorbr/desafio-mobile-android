package com.example.home_presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.items
import com.example.home_domain.model.Character
import com.example.home_presentation.screen.components.CharacterListItemComponent
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeListScreenSuccessLoaded(
    flowCharacters: Flow<PagingData<Character>>,
    retryAction: () -> Unit
) {
    val spacing = LocalSpacing.current
    val lazyCharacters = flowCharacters.collectAsLazyPagingItems()
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
        item {
            lazyCharacters.apply {
                when {
                    loadState.refresh is LoadState.Loading -> LoadingComponent()
                    loadState.append is LoadState.Loading -> LoadingComponent()
                    loadState.refresh is LoadState.Error -> RetryButtonComponent(
                        onClick = retryAction
                    )
                    loadState.append is LoadState.Error -> RetryButtonComponent(
                        onClick = retryAction
                    )
                }
            }
        }
    }
}
