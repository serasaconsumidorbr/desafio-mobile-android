package com.example.domain.heroes.model

data class Page(
    val page: Int,
    val nextPage : Int,
    val hero : List<Hero>
)
