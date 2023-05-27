package com.victorvgc.mymarvelheros.home.domain.domain

data class HeroesPage(
    val pageSize: Int,
    val pageOffset: Int,
    val heroesList: List<Hero>
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val DEFAULT_INIT_PAGE_SIZE = 5
    }
}
