package com.example.marvelheroes.domain.characters

import java.io.Serializable

data class Url(
    val type: String,
    val url: String
) : Serializable
