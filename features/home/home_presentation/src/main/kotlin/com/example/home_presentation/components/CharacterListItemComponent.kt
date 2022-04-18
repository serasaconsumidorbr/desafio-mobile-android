package com.example.home_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.home_domain.model.Character
import com.example.home_presentation.R
import com.example.ui.components.spacers.VerticalSpacer
import com.example.ui.theme.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterListItemComponent(
    character: Character,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardHeight: Dp = 64.dp,
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = modifier.height(cardHeight),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            CharacterImageComponent(
                imageUrl = character.imageUrl,
                name = character.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
                    .padding(spacing.small)
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(spacing.extraSmall),
                color = Color.Red
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spacing.small)
            ) {
                CharacterNameItemComponent(character.name)
                VerticalSpacer(spacing.small)
                CharacterDescriptionItemComponent(character.description)
            }
        }
    }
}

@Composable
private fun CharacterNameItemComponent(name: String) {
    Text(
        text = name,
        style = TextStyle(
            fontSize = 14.sp,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun CharacterDescriptionItemComponent(description: String) {
    Text(
        text = description,
        style = TextStyle(
            fontSize = 12.sp,
        ),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Light,
        color = Color.Gray
    )
}

@Composable
private fun CharacterImageComponent(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.marvel_placeholder),
        contentDescription = name,
        contentScale = ContentScale.Fit
    )
}