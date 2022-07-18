package com.ncz.desafio_mobile_android.infrastructure.dto

data class ResponseApi(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: Data
)


