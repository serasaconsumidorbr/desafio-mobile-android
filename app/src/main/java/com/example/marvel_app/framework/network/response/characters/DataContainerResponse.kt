package com.example.marvel_app.framework.network.response.characters

data class DataContainerResponse(
    val offset: Int,
    val total: Int,
    val results: List<CharacterResponse>
)
