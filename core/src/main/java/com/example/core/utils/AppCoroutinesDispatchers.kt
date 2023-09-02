package com.example.core.utils

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutinesDispatchers (
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher,
)