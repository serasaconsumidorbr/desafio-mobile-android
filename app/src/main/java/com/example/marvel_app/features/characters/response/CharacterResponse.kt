package com.example.marvel_app.features.characters.response

import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.framework.network.response.ThumbnailResponse
import com.example.marvel_app.framework.network.response.getHttpsUrl
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toCharacterModel(): Character {
    return Character(
        id = this.id,
        name = this.name,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}
