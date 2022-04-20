package com.example.home_data.remote.mapper

interface CharacterSafeStringMapper {
    fun mapFrom(
        stringToMap: String?,
        safeValue: String
    ): String
}