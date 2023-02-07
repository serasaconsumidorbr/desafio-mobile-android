package com.desafio.android.ui.screen.home.content

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desafio.android.R
import com.desafio.android.core.extension.isLastItemVisible
import com.desafio.android.domain.entity.MarvelCharacter
import com.desafio.android.ui.screen.home.HomeInteraction
import com.desafio.android.ui.screen.home.content.component.MarvelCharacterBanner
import com.desafio.android.ui.screen.home.content.component.MarvelCharacterListItem
import com.desafio.android.ui.screen.home.content.state.HomeScreenState
import com.desafio.android.ui.shared.TopBar
import com.desafio.android.ui.theme.Gray2
import com.desafio.android.ui.theme.MaterialTypography
import com.desafio.android.ui.theme.Red2
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    interact: (HomeInteraction) -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                TopBar(
                    onOptionsClicked = {
                        // TODO()
                    },
                    onBellClicked = {
                        // TODO()
                    }
                )
            }
        }
    ) { padding ->
        val lazyListState = rememberLazyListState()

        val isLastItemVisible by remember { derivedStateOf { lazyListState.isLastItemVisible() } }
        val firstVisibleItemScrollOffset by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }

        if ((firstVisibleItemScrollOffset > 0) && state.characters.isNotEmpty() && !state.isLastItemVisible && isLastItemVisible) {
            interact(HomeInteraction.GetCharacters)
        }

        LaunchedEffect(key1 = isLastItemVisible, block = {
            interact(HomeInteraction.SetIsLastItemVisible(
                isVisible = isLastItemVisible
            ))
        })

        var bannerCharacters: List<MarvelCharacter> by remember { mutableStateOf(emptyList()) }
        var listCharacters: List<MarvelCharacter> by remember { mutableStateOf(emptyList()) }

        LaunchedEffect(key1 = state.characters, block = {
            bannerCharacters = state.getBannerCharacters()
            listCharacters = state.getListCharacters()
        })

        SwipeRefresh(
            modifier = Modifier
                .padding(padding),
            state = rememberSwipeRefreshState(
                isRefreshing = state.getCharactersCaseExecution.isLoading()
            ),
            onRefresh = {
                interact(HomeInteraction.ResetCharacters)
                interact(HomeInteraction.GetCharacters)
            }
        ) {
            Column(
                modifier = Modifier
                    .background(Gray2)
            ) {
                if (state.characters.isNotEmpty()) {
                    var index by remember { mutableStateOf(0) }

                    LazyColumn(
                        state = lazyListState
                    ) {
                        item {
                            Row(
                                modifier = Modifier.padding(
                                    top = 16.dp,
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 8.dp
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.general_characters),
                                    style = MaterialTypography.Poppins.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 18.sp
                                    ),
                                    color = Red2
                                )
                            }
                        }
                        
                        item {
                            ScrollableTabRow(
                                selectedTabIndex = index,
                                backgroundColor = Color.Transparent,
                                edgePadding = 16.dp,
                                indicator = {},
                                divider = {},
                                tabs = {
                                    bannerCharacters.forEachIndexed { i, character ->
                                        MarvelCharacterBanner(
                                            modifier = Modifier
                                                .padding(
                                                    start = if (i == 0) 0.dp else 16.dp
                                                ),
                                            character = character,
                                            onClick = {
                                                index = i
                                            }
                                        )
                                    }
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        items(
                            items = listCharacters,
                            itemContent = { character ->
                                Column(
                                    modifier = Modifier
                                        .padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            top = if (character == listCharacters.first()) 0.dp else 16.dp
                                        )
                                ) {
                                    MarvelCharacterListItem(character = character)
                                }
                            }
                        )

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    HomeScreenContent(
        state = HomeScreenState(),
        interact = {},
    )
}