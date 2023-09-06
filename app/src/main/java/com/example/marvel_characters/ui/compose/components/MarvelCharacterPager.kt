package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.marvel_characters.Constants.PAGER_PAGE_COUNT
import com.example.marvel_characters.R
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import kotlin.math.absoluteValue



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarvelCharacterPager(modifier: Modifier = Modifier, marvelCharacters: List<MarvelCharacter>) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)


    val pagerState = rememberPagerState(pageCount = {
        PAGER_PAGE_COUNT
    })
    Column() {

        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(3)
        )
        HorizontalPager(state = pagerState,    flingBehavior = fling) { page ->
            OutlinedCard(
                Modifier.align(Alignment.CenterHorizontally).padding(smallPadding)
                    .fillMaxWidth()
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.3f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {

                    MarvelCharacterItem(marvelCharacter =marvelCharacters[page])
            }

        }
        PagerIndicator(pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicator(
    pagerState: PagerState
) {
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),


        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(PAGER_PAGE_COUNT) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primaryContainer else  MaterialTheme.colorScheme.secondaryContainer
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(20.dp)

            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Composable
fun MarvelCharacterPagerPreview() {
    MarvelCharactersTheme {
        Surface {
            MarvelCharacterPager(marvelCharacters = listOf())
        }
    }
}