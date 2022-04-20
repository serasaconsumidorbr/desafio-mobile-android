package com.example.util.api

sealed class ImageVariant(private val prefix: String) {

    enum class Type {
        SMALL,
        MEDIUM,
        LARGE,
        XLARGE,
        FANTASTIC,
        UNCANNY,
        INCREDIBLE,
        AMAZING
    }

    fun getValue(type: Type) = "${prefix.lowercase()}_${type.name.lowercase()}"

    object Portrait : ImageVariant(Portrait::class.java.simpleName)
    object Standard : ImageVariant(Standard::class.java.simpleName)
    object Landscape : ImageVariant(Landscape::class.java.simpleName)
}