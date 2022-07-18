package com.ncz.desafio_mobile_android.infrastructure.dto


data class Data(
    val offset: String?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterDto>?
)