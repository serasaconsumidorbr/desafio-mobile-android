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
import com.example.home_presentation.components.carousel.HomeCarouselComponent
import com.example.home_presentation.components.carousel.HomeCarouselUiState
import com.example.home_presentation.components.carousel.HomeCarouselViewModel
import com.example.home_presentation.components.list.HomeListUiState
import com.example.home_presentation.components.list.HomeListViewModel
import com.example.home_presentation.components.list.homeInfinityListComponent
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.Dimensions
import com.example.ui.theme.LocalSpacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeListViewModel: HomeListViewModel = hiltViewModel(),
    carouselViewModel: HomeCarouselViewModel = hiltViewModel(),
) {
    val homeListUiState by homeListViewModel.uiState.collectAsState()
    val carouselUiState by carouselViewModel.uiState.collectAsState()
    HomeScreen(
        carouselUiState = carouselUiState,
        homeListUiState = homeListUiState,
        retryAction = {
            homeListViewModel.dispatchEvent(HomeScreenUiEvent.RetryLoad)
            carouselViewModel.dispatchEvent(HomeScreenUiEvent.RetryLoad)
        },
        carouselCardClick = { carouselViewModel.dispatchEvent(HomeScreenUiEvent.CardClick {}) },
        listCardClick = { homeListViewModel.dispatchEvent(HomeScreenUiEvent.CardClick {}) }
    )
}

@Composable
private fun HomeScreen(
    carouselUiState: HomeCarouselUiState,
    retryAction: () -> Unit,
    carouselCardClick: () -> Unit,
    listCardClick: () -> Unit,
    homeListUiState: HomeListUiState,
) {
    val lazyCharacters = (homeListUiState as? HomeListUiState.Success)
        ?.data?.collectAsLazyPagingItems()
    val dimensions = LocalSpacing.current
    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = false
        ),
        onRefresh = retryAction
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                VerticalSpacer()
            }
            item {
                CarouselComponent(
                    state = carouselUiState,
                    retryAction = retryAction,
                    openCharacterDetails = carouselCardClick
                )
            }
            item {
                VerticalSpacer()
            }
            lazyCharacters?.let {
                infinityListComponent(
                    lazyCharacters = it,
                    dimensions = dimensions,
                    retryAction = retryAction,
                    listCardClick = listCardClick
                )
            }
        }
    }
}

@Composable
private fun CarouselComponent(
    state: HomeCarouselUiState,
    retryAction: () -> Unit,
    openCharacterDetails: () -> Unit,
) {
    (state as? HomeCarouselUiState.Success)?.let {
        HomeCarouselComponent(
            characters = it.data,
            openCharacterDetails = openCharacterDetails,
            contentPadding = LocalSpacing.current.medium
        )
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
    listCardClick: () -> Unit,
) = homeInfinityListComponent(
    lazyCharacters = lazyCharacters,
    dimensions = dimensions,
    retryAction = retryAction,
    listCardClick = listCardClick
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
