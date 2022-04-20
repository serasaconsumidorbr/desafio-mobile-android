package com.br.leandro.marvel_hero_app.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart


fun <T : Any> Flow<ActionResult<T>>.prepareLoadingStates() =
    this
        .onStart { emit(Loading(isLoading = true)) }
        .onCompletion { emit(Loading(isLoading = false)) }