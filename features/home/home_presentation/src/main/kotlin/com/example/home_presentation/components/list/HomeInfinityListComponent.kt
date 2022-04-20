package com.example.home_presentation.components.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_presentation.components.list.components.CharacterListItemComponent
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.Dimensions

fun LazyListScope.homeInfinityListComponent(
    dimensions: Dimensions,
    lazyCharacters: LazyPagingItems<CharacterHomeUiModel>,
    retryAction: () -> Unit,
    listCardClick: () -> Unit
) {
    items(lazyCharacters) { character ->
        character?.let {
            Box(modifier = Modifier.padding(horizontal = dimensions.medium)) {
                CharacterListItemComponent(
                    characterHomeUiModel = it,
                    onClick = listCardClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            VerticalSpacer()
        }
    }
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.medium),
            contentAlignment = Alignment.Center
        ) {
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
