package com.updeveloped.desafiomarvel.data.response.dto

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataResponse,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)