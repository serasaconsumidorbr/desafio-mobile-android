package com.example.marvel_app.features.characters.response.characters

import com.google.gson.annotations.SerializedName

data class DataContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)
