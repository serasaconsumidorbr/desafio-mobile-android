package com.example.marvel_characters.ui.compose.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.marvel_characters.Constants.PAGER_TEST_TAG
import com.example.marvel_characters.R
import com.example.marvel_characters.Samples
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.ui.compose.theme.MarvelCharactersTheme
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarvelCharacterPager(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    enableAutoScroll: Boolean = false,
    navigateToCharacter: (String) -> Unit
) {
    val smallPadding = dimensionResource(id = R.dimen.small_padding)


    val pagerState = rememberPagerState(pageCount = {
        characters.size
    })

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .wrapContentHeight()
    ) {


        HorizontalPager(state = pagerState, modifier = modifier.testTag(PAGER_TEST_TAG)) { page ->
            OutlinedCard(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(smallPadding)
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.3f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                val currentMarvelCharacter =  characters[page]
                MarvelCharacterPagerItem(
                    character = currentMarvelCharacter,
                    modifier = Modifier.clickable { navigateToCharacter(currentMarvelCharacter.id) })
            }
        }

        PagerIndicator(modifier = Modifier.padding(smallPadding), pagerState = pagerState)

        if (enableAutoScroll) {
            EnableAutoScroll( pagerState)
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun EnableAutoScroll(pagerState: PagerState) {
    val maxPageIndex = pagerState.pageCount - 1
    pagerState.settledPage.let {
        LaunchedEffect(it) {
            val secondsToWait = 10.toDuration(DurationUnit.SECONDS)
            delay(secondsToWait)

            if (it == maxPageIndex) {
                pagerState.scrollToPage(0)

            } else {
                pagerState.animateScrollToPage(it + 1)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier
            .fillMaxWidth(),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.small_padding))
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
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
            MarvelCharacterPager(
                characters = Samples.charactersWithNonRepeatedElements,
                navigateToCharacter = {  }
            )
        }
    }
}