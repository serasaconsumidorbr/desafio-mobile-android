package com.example.marvel.model

import java.io.Serializable

data class Characters(
    val id: String,
    val name: String,
    val description: String?,
    val imageSrc: String?,
    val stories: List<String>?
): Serializable


