package com.desafio.android.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.desafio.android.ui.screen.home.content.HomeScreenContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenContent(
        state = viewModel.state,
        interact = viewModel::handleUserInteraction
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}