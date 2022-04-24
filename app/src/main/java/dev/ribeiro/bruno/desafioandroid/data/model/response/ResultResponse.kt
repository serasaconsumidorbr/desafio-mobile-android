package dev.ribeiro.bruno.desafioandroid.data.model.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)