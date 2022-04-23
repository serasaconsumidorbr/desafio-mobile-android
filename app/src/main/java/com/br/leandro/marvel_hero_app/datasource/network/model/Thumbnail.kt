package com.br.leandro.marvel_hero_app.datasource.network.model

/** 100x100px */
private const val small = "/standard_medium."

/** 500px */
private const val detail = "/detail."

/** 216x324px */
private const val portrait = "/portrait_incredible."

/**
 * In order to make the APP with greater performance and waste the minimum
 * bandwidth possible, I searched for the smallest necessary image size
 * for each occasion.
 */
data class Thumbnail(
    private val extension: String,
    private val path: String
) {
    fun iconImage() = path + small + extension
    fun detailImage() = path + detail + extension
    fun portraitImage() = path + portrait + extension
}