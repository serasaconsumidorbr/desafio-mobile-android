package com.example.marvelheroes.data.retrofit

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class DispatchersProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
}
