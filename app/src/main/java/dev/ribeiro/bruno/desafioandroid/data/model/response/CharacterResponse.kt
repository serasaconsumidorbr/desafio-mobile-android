package dev.ribeiro.bruno.desafioandroid.data.model.response

import com.google.gson.annotations.SerializedName
import dev.ribeiro.bruno.desafioandroid.data.model.response.DataResponse

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