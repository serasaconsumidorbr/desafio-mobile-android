package com.victorvgc.mymarvelheros.home.data.model.remote

import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

data class RemoteHeroPage(
    val offset: Int,
    val limit: Int,
    val results: List<RemoteHero>
) {
    fun toModel(): HeroesPage {
        return HeroesPage(
            pageSize = limit,
            pageOffset = offset,
            heroesList = results.map { it.toModel() }
        )
    }
}