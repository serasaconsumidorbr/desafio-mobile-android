package com.example.marvelheroes.presentation.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.domain.characters.Character

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ContentHome(
    modifier: Modifier = Modifier,
    listHeroesVertically: List<Character>,
    listHeroesHorizontally: List<Character>,
    onClickHorizontal: (Character) -> Unit,
    onClickVertical: (Character) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        stickyHeader {
            LazyRow {
                items(listHeroesHorizontally) { item ->
                    Card(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(12.dp),
                        onClick = {
                            onClickHorizontal(item)
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(0.5f)
                        ),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color.Black.copy(alpha = 0.7f))
                        ) {
                            Text(
                                text = item.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(20.dp)
                                    .height(48.dp)
                                    .background(Color.Red)
                            )
                        }
                    }
                }
            }

        }
        items(listHeroesVertically) { item ->
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.Red)
                    .clickable { onClickVertical(item) }
            )
        }
    }

}