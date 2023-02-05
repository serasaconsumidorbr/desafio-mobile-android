package com.example.marvelheroes.domain.models.characters

import java.io.Serializable

data class Url(
    val type: String,
    val url: String
) : Serializable
