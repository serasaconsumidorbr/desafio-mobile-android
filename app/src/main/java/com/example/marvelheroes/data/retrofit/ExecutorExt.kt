package com.example.marvelheroes.data.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> executor(ex: suspend () -> T): T =
    withContext(Dispatchers.IO) { ex() }