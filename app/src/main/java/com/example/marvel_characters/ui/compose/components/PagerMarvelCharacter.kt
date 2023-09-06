package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.baseproject.R
import com.example.baseproject.domain.News
import com.example.baseproject.ui.compose.BaseProjectTheme

@Composable
fun NewsListItem(news: News, navigateToNews: (String) -> Unit) {
    val mediumPadding = dimensionResource(id = R.dimen.medium_padding)

    news.apply {
        Column(
            Modifier
                .padding(mediumPadding)
                .clickable { navigateToNews(news.url!!) }
        ) {
            NewsImage(urlToImage)
            title?.let { Text(it, style = MaterialTheme.typography.titleLarge) }
            author?.let { Text(it, style = MaterialTheme.typography.labelMedium) }
            sourceName?.let { Text(it, style = MaterialTheme.typography.labelSmall) }
        }

    }
}

@Composable
private fun NewsImage(urlToImage: String?) {
    val placeholder = R.drawable.news_image_placeholder

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(urlToImage)
            .crossfade(true)
            .build(),

        contentScale = ContentScale.FillWidth,
        error = painterResource(placeholder),
        placeholder = painterResource(placeholder),
        contentDescription = stringResource(R.string.content_description_media),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 194.dp)
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO, name = "DefaultPreviewLight"
)

@Composable
fun NewsPreview() {
    BaseProjectTheme {
        val user = News(
            author = "Aurora Maria",
            content = "Deputado se matou",
            description = "Uma longa história",
            sourceName = "uol.com.br",
            title = "Deputado se mata",
            url = "www.uol.com/deputado-se-mata",
            urlToImage = null
        )
        NewsListItem(user, { })
    }
}