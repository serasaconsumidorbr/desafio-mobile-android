package com.victorvgc.mymarvelheros.home.data.model.remote

import com.victorvgc.mymarvelheros.home.data.utils.INCREDIBLE_LANDSCAPE
import com.victorvgc.mymarvelheros.home.domain.domain.Hero

data class RemoteHero(
    val id: Int,
    val name: String,
    val thumbnail: RemoteImageResource
) {
    fun toModel(): Hero {
        return Hero(
            id = id,
            name = name,
            imageUrl = "${thumbnail.path}$INCREDIBLE_LANDSCAPE${thumbnail.extension}"
        )
    }
}