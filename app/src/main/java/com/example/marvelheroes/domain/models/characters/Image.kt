package com.example.marvelheroes.domain.models.characters

import java.io.Serializable

data class Image(
    val path: String,
    val extension: String,
) : Serializable

