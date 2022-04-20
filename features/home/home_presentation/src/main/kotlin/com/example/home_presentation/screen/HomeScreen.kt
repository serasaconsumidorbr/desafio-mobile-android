package com.example.home_presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home_presentation.carousel.HomeCarouselComponent
import com.example.home_presentation.carousel.HomeCarouselViewModel
import com.example.home_presentation.carousel.HomeCarouselUiState
import com.example.home_presentation.list.HomeInfinityListComponent
import com.example.home_presentation.list.HomeListUiState
import com.example.home_presentation.list.HomeListViewModel
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent
import com.example.ui.components.spacers.VerticalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeListViewModel: HomeListViewModel = hiltViewModel(),
    carouselViewModel: HomeCarouselViewModel = hiltViewModel(),
) {
    val homeListUiState by homeListViewModel.uiState.collectAsState()
    val carouselUiState by carouselViewModel.uiState.collectAsState()
    val retryAction = { homeListViewModel.dispatchEvent(HomeScreenUiEvent.RetryLoad) }
    Column {
        CarouselComponent(
            state = carouselUiState,
            retryAction = retryAction
        )
        VerticalSpacer()
        InfinityListComponent(
            state = homeListUiState,
            retryAction = retryAction
        )
    }
}

@Composable
private fun CarouselComponent(
    state: HomeCarouselUiState,
    retryAction: () -> Unit
) {
    (state as? HomeCarouselUiState.Success)?.let {
        HomeCarouselComponent(it.data)
    } ?: (state as? HomeCarouselUiState.Loading)?.let {
        LoadingComponent()
    } ?: (state as? HomeCarouselUiState.Error)?.let {
        HomeRetryComponent(retryAction)
    }
}

@Composable
private fun InfinityListComponent(
    state: HomeListUiState,
    retryAction: () -> Unit,
) {
    (state as? HomeListUiState.Success)?.let {
        HomeInfinityListComponent(
            flowCharacters = it.data,
            retryAction = retryAction
        )
    } ?: (state as? HomeListUiState.Loading)?.let {
        LoadingComponent()
    } ?: (state as? HomeListUiState.Error)?.let {
        HomeRetryComponent(retryAction)
    }
}

@Composable
private fun HomeRetryComponent(retryAction: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RetryButtonComponent(onClick = retryAction)
    }
}
