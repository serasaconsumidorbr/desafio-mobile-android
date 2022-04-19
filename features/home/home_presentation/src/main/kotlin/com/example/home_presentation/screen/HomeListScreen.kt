package com.example.home_presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home_presentation.HomeListUiState
import com.example.home_presentation.HomeListViewModel
import com.example.ui.components.LoadingComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeListScreen(viewModel: HomeListViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    (state as? HomeListUiState.Success)?.let {
        HomeListScreenSuccessLoaded(it.data)
    } ?: (state as? HomeListUiState.Loading)?.let {
        LoadingComponent()
    }
}
