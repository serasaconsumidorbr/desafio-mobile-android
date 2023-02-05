package com.example.marvelheroes.presentation.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelheroes.domain.models.characters.Character

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
)
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(12.dp),
                            onClick = {
                                onClickHorizontal(item)
                            }
                        ) {
                            Box {
                                AsyncImage(
                                    model = "${item.thumbnail.path}.${item.thumbnail.extension}",
                                    contentDescription = item.description,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(200.dp)
                                )

                                Text(
                                    text = item.name,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Black.copy(alpha = .3f))
                                        .padding(10.dp),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Justify
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        items(listHeroesVertically) { item ->
            Box(
                modifier = Modifier.background(Color.Black)
            ) {
                Card(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(5.dp),
                    onClick = {
                        onClickVertical(item)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(0.5f)
                    ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item.name,
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                }
            }
        }
    }
}