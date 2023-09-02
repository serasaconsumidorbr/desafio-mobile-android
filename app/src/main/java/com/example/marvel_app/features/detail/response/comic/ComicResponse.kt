package com.example.marvel_app.features.detail.response.comic

import com.example.core.features.details.domain.Comic
import com.example.marvel_app.framework.network.response.ThumbnailResponse
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
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
            .replace("http", "https")
    )
}
