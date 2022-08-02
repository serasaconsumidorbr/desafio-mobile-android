package com.developer.marvel.infrastructure.dto.service

data class ResponseDto<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: DataDto<T>
)