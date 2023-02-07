package com.desafio.android.ui.screen.home.content.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desafio.android.R
import com.desafio.android.domain.entity.MarvelCharacter
import com.desafio.android.ui.shared.WebImage
import com.desafio.android.ui.theme.Gray2
import com.desafio.android.ui.theme.Gray3
import com.desafio.android.ui.theme.MaterialTypography
import com.desafio.android.ui.theme.Red2

@Composable
fun MarvelCharacterListItem(
    modifier: Modifier = Modifier,
    character: MarvelCharacter,
    onClick: ((MarvelCharacter) -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp,
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .background(Gray3)
                .clickable(
                    enabled = onClick != null,
                    onClick = {
                        onClick?.invoke(character)
                    }
                )
        ) {
            Box {
                WebImage(
                    modifier = Modifier
                        .height(215.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    url = character.getThumbnailUrl(),
                    contentDescription = "Character Image"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(215.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Red2
                                )
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .background(Red2)
                    .padding(2.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(Gray2)
                            .fillMaxWidth()
                    ) {
                        Card(
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 10.dp,
                                bottomStart = 0.dp
                            ),
                            elevation = 1.dp,
                            backgroundColor = Color.Transparent
                        ) {
                            Box(
                                modifier = modifier
                                    .background(Red2)
                                    .padding(
                                        vertical = 6.dp,
                                        horizontal = 12.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = character.name,
                                    style = MaterialTypography.Poppins.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                    ),
                                    color = Color.White
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(
                                    alignment = Alignment.CenterEnd
                                )
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                painter = painterResource(id = R.drawable.ic_heart),
                                contentDescription = "Heart Icon"
                            )
                        }
                    }

                    Card(
                        modifier = modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        ),
                        elevation = 1.dp,
                        backgroundColor = Color.Transparent
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Gray2)
                                .padding(13.dp)
                        ) {
                            if (character.description.isNotEmpty()) {
                                Text(
                                    text = character.description,
                                    style = MaterialTypography.Poppins.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                    ),
                                    color = Color.White
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(16.dp)
                                )
                            }

                            Row {
                                Column {
                                    MarvelCharacterStatisticIcon(
                                        icon = R.drawable.ic_comics,
                                        text = stringResource(id = R.string.general_comics) + ": " + character.comics.available.toString()
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .height(8.dp)
                                    )

                                    MarvelCharacterStatisticIcon(
                                        icon = R.drawable.ic_events,
                                        text = stringResource(id = R.string.general_events) + ": " + character.events.available.toString()
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Column {
                                    MarvelCharacterStatisticIcon(
                                        icon = R.drawable.ic_series,
                                        text = stringResource(id = R.string.general_series) + ": " + character.series.available.toString()
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .height(8.dp)
                                    )

                                    MarvelCharacterStatisticIcon(
                                        icon = R.drawable.ic_stories,
                                        text = stringResource(id = R.string.general_stories) + ": " + character.stories.available.toString()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MarvelCharacterListItemPreview() {
    MarvelCharacterListItem(
        character = MarvelCharacter.getMockedCharacter()
    )
}