package com.developer.marvel.infrastructure.dto.service

import com.google.gson.GsonBuilder

data class ErrorApiDto(
    val code: String,
    val message: String,
) {

    companion object {
        fun parse(json: String?): ErrorApiDto {
            val mapper = GsonBuilder().create()
            return mapper.fromJson(json, ErrorApiDto::class.java)
        }
    }
}
