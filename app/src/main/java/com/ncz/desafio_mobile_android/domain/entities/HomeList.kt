package com.ncz.desafio_mobile_android.domain.entities

import com.ncz.desafio_mobile_android.domain.entities.character.Character

data class HomeList(
    val heroes: List<Character>? = null,
    val character: List<Character>? = null
)