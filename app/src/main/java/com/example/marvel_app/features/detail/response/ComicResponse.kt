package com.example.marvel_app.features.detail.response

import com.example.core.features.details.domain.Comic
import com.example.marvel_app.features.characters.response.toCharacterModel
import com.example.marvel_app.framework.network.response.ThumbnailResponse
import com.example.marvel_app.framework.network.response.getHttpsUrl
import com.google.gson.annotations.SerializedName

data class ComicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun ComicResponse.toComicModel(): Comic {
    return Comic(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}
