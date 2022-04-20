package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.mapper.CharacterSafeStringMapper

class CharacterSafeString : CharacterSafeStringMapper {
    override fun mapFrom(stringToMap: String?, safeValue: String): String =
        if (stringToMap.isNullOrEmpty() || stringToMap.isBlank()) {
            safeValue
        } else {
            stringToMap
        }
}