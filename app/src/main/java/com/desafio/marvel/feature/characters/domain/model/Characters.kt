package com.desafio.marvel.feature.characters.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResponse(
    @SerializedName("data") val data: CharactersResponse
) : Serializable

data class CharactersResponse(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<ResultsResponse>
) : Serializable

data class ResultsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse
): Serializable

data class ThumbnailResponse(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
): Serializable