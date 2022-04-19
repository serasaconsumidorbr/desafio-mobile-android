package com.example.home_presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home_presentation.HomeListUiEvent
import com.example.home_presentation.HomeListUiState
import com.example.home_presentation.HomeListViewModel
import com.example.ui.components.LoadingComponent
import com.example.ui.components.RetryButtonComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeListScreen(viewModel: HomeListViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val retryAction = { viewModel.dispatchEvent(HomeListUiEvent.RetryLoad) }
    (state as? HomeListUiState.Success)?.let {
        HomeListScreenSuccessLoaded(
            flowCharacters = it.data,
            retryAction = retryAction
        )
    } ?: (state as? HomeListUiState.Loading)?.let {
        LoadingComponent()
    } ?: (state as? HomeListUiState.Error)?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RetryButtonComponent(onClick = retryAction)
        }
    }
}
