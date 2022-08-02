package com.developer.marvel.domain.failures

sealed class MarvelFailures(override val message: String) : Exception()