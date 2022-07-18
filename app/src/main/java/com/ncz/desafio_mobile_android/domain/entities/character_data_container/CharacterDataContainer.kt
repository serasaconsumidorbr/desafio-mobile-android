package com.ncz.desafio_mobile_android.domain.entities.character_data_container

import com.ncz.desafio_mobile_android.domain.entities.character.Character

data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<Character>,
)