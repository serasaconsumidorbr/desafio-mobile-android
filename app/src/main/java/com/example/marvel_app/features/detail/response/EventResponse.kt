package com.example.marvel_app.features.detail.response

import com.example.core.features.details.domain.Event
import com.example.marvel_app.framework.network.response.ThumbnailResponse
import com.example.marvel_app.framework.network.response.getHttpsUrl
import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun EventResponse.toEventModel(): Event{
    return Event(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}
