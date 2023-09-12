package com.example.marvel_characters.ui.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.marvel_characters.R

@Composable
fun FullScreenCenteredProgress(showLogo: Boolean = false) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val smallPadding = dimensionResource(id = R.dimen.small_padding)

        if (showLogo) {
            MarvelLogo(Modifier.padding(smallPadding))
        }

        CircularProgressIndicator(
            Modifier
                .wrapContentSize()
                .padding(bottom = smallPadding)
        )

        Text(stringResource(id = R.string.loading))
    }
}

@Composable
fun MarvelLogo(modifier: Modifier) {

    val loadingLogoImageSize = dimensionResource(id = R.dimen.loading_image_size)

    Image(
        modifier = modifier
            .size(loadingLogoImageSize),
        painter = painterResource(id = R.drawable.marvel_m_placeholder),
        contentDescription = stringResource(
            id = R.string.loading_marvel_m_logo
        )
    )
}