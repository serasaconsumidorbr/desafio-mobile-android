package com.example.marvel_characters.network

class CharactersResultDataInfo(
    val offset: Int,
    val limit: Int,
    val total: Int
) {
    fun nextPageOffset() = offset + limit
    fun hasNextPage() = (offset + limit) < total
}