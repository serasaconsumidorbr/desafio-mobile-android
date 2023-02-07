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

@Composable
fun MarvelCharacterBanner(
    modifier: Modifier = Modifier,
    character: MarvelCharacter,
    onClick: ((MarvelCharacter) -> Unit)? = null
) {
    Card(
        modifier = modifier
            .width(250.dp)
            .height(145.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp,
        backgroundColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray3)
                .clickable(
                    enabled = onClick != null,
                    onClick = {
                        onClick?.invoke(character)
                    }
                )
        ) {
            WebImage(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                url = character.getThumbnailUrl(),
                contentDescription = "Character Image"
            )

            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Gray2.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    )
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp
                    )
                    .align(
                        alignment = Alignment.BottomStart
                    )
            ) {
                Text(
                    text = character.name,
                    style = MaterialTypography.Poppins.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(
                        alignment = Alignment.BottomEnd
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
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MarvelCharacterBannerPreview() {
    MarvelCharacterBanner(
        character = MarvelCharacter.getMockedCharacter()
    )
}