package com.developer.marvel.infrastructure.dto.service

data class DataDto<T>(
    val offset: String?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>?
)