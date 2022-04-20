package com.br.leandro.marvel_hero_app.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiResponse<out T>(
    @Json(name = "attributionHTML")
    val attributionHTML: String = "",
    @Json(name = "attributionText")
    val attributionText: String = "",
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "copyright")
    val copyright: String = "",
    @Json(name = "etag")
    val etag: String = "",
    @Json(name = "status")
    val status: String = "",
    @Json(name = "data")
    val data: DataResponse<T>
)

@JsonClass(generateAdapter = true)
data class DataResponse<out T>(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "results")
    val results: List<T>
)

@JsonClass(generateAdapter = true)
data class HeroResponse(
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail()
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "extension")
    val extension: String = "",
    @Json(name = "path")
    val path: String = ""
)