package com.desafio.android.core.extension

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isLastItemVisible() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1