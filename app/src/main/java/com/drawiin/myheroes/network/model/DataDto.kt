package com.drawiin.myheroes.network.model


import com.drawiin.myheroes.network.model.CharacterDto
import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<CharacterDto>?,
    @SerializedName("total")
    val total: Int?
)