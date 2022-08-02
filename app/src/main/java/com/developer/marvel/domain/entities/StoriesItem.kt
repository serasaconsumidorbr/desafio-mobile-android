package com.developer.marvel.domain.entities

enum class StoriesItemType(private val type: String) {
    COVER("cover"),
    INTERIOR_STORY("interiorStory"),

    UNKWON("unkwon");

    companion object {
        fun toTypeObject(type: String): StoriesItemType {
            return values().firstOrNull { it.type.equals(type, ignoreCase = true) } ?: UNKWON
        }
    }
}

data class StoriesItem(
    val resourceURI: String,
    val name: String,
    val type: StoriesItemType
)
