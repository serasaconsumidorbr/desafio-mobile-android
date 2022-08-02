package com.developer.marvel.domain.entities

enum class JobItemType(private val type: String) {
    COVER("cover"),
    INTERIOR_STORY("interiorStory"),

    UNKWON("unkwon");

    companion object {
        fun toTypeObject(type: String): JobItemType {
            return values().firstOrNull { it.type.equals(type, ignoreCase = true) } ?: UNKWON
        }
    }
}

data class JobItem(
    val resourceURI: String,
    val name: String,
    val type: JobItemType? = null
)
