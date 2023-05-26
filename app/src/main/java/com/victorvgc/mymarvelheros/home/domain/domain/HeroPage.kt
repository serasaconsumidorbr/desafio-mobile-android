package com.victorvgc.mymarvelheros.home.domain.domain

data class HeroPage(
    val pageSize: Int,
    val pageOffset: Int,
    val heroesList: List<Hero>
)
