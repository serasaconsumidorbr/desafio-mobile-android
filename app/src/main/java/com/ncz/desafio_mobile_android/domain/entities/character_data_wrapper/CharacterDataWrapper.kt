package com.ncz.desafio_mobile_android.domain.entities.character_data_wrapper

import com.ncz.desafio_mobile_android.domain.entities.character_data_container.CharacterDataContainer

data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharacterDataContainer,
    val etag: String,
)