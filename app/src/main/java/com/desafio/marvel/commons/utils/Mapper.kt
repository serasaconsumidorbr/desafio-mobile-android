package com.desafio.marvel.commons.utils

interface Mapper<S, T> {
    fun map(source: S): T
}