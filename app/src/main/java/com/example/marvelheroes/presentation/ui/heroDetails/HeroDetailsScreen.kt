package com.example.marvelheroes.presentation.ui.heroDetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailScreen(
    paramsScreenName: String,
    paramsScreenImage: String,
    paramsScreenDescription: String,
    changeScreen: Boolean,
    onClickCard: (Boolean) -> Unit = {}
) {
    AnimatedVisibility(visible = changeScreen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                onClick = {
                    onClickCard(false)
                }
            ) {
                Box {
                    AsyncImage(
                        model = paramsScreenImage,
                        contentDescription = paramsScreenDescription,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = .5f))
                    ) {

                        item {
                            Text(
                                text = paramsScreenName,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Justify
                                )
                            )

                            if (paramsScreenDescription.isNotEmpty())
                                Text(
                                    text = paramsScreenDescription,
                                    modifier = Modifier
                                        .fillMaxSize()
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
}