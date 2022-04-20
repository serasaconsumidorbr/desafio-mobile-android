package com.example.home_presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_presentation.carousel.HomeCarouselComponent
import com.example.home_presentation.carousel.HomeCarouselUiState
import com.example.home_presentation.carousel.HomeCarouselViewModel
import com.example.home_presentation.list.HomeListUiState
import com.example.home_presentation.list.HomeListViewModel
import com.example.home_presentation.list.homeInfinityListComponent
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.Dimensions
import com.example.ui.theme.LocalSpacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeListViewModel: HomeListViewModel = hiltViewModel(),
    carouselViewModel: HomeCarouselViewModel = hiltViewModel(),
) {
    val homeListUiState by homeListViewModel.uiState.collectAsState()
    val carouselUiState by carouselViewModel.uiState.collectAsState()
    val retryAction = { homeListViewModel.dispatchEvent(HomeScreenUiEvent.RetryLoad) }
    HomeScreen(
        carouselUiState = carouselUiState,
        retryAction = retryAction,
        homeListUiState = homeListUiState
    )
}

@Composable
private fun HomeScreen(
    carouselUiState: HomeCarouselUiState,
    retryAction: () -> Unit,
    homeListUiState: HomeListUiState,
) {
    val lazyCharacters = (homeListUiState as? HomeListUiState.Success)
        ?.data?.collectAsLazyPagingItems()
    val dimensions = LocalSpacing.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            VerticalSpacer()
        }
        item {
            CarouselComponent(
                state = carouselUiState,
                retryAction = retryAction
            )
        }
        item {
            VerticalSpacer()
        }
        lazyCharacters?.let {
            infinityListComponent(
                lazyCharacters = it,
                dimensions = dimensions,
                retryAction = retryAction
            )
        }
    }
}

@Composable
private fun CarouselComponent(
    state: HomeCarouselUiState,
    retryAction: () -> Unit,
) {
    (state as? HomeCarouselUiState.Success)?.let {
        HomeCarouselComponent(it.data)
    } ?: (state as? HomeCarouselUiState.Loading)?.let {
        LoadingComponent()
    } ?: (state as? HomeCarouselUiState.Error)?.let {
        HomeRetryComponent(retryAction)
    }
}

private fun LazyListScope.infinityListComponent(
    lazyCharacters: LazyPagingItems<CharacterHomeUiModel>,
    dimensions: Dimensions,
    retryAction: () -> Unit,
) = homeInfinityListComponent(
    lazyCharacters = lazyCharacters,
    dimensions = dimensions,
    retryAction = retryAction
)

@Composable
private fun HomeRetryComponent(retryAction: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RetryButtonComponent(onClick = retryAction)
    }
}
