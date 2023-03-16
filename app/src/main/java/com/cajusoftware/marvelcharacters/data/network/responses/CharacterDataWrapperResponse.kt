package com.cajusoftware.marvelcharacters.data.network.responses

import com.cajusoftware.marvelcharacters.BuildConfig
import com.squareup.moshi.Json

data class CharacterDataWrapperResponse(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val data: CharacterDataContainerResponse?,
    @Json(name = "etag") val eTag: String?
)

data class CharacterDataContainerResponse(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterResponse>?,
) {
    val nextKey = if ((offset ?: 1) > (total ?: 0)) null else BuildConfig.SERVICE_PAGE_SIZE + (offset ?: 0)
    val hasNext = (BuildConfig.SERVICE_PAGE_SIZE + (offset ?: 0)) <= (total ?: 0)
}
