package com.example.marvelheroes.data.mock

data class MockComics(
    val available: Int = 12,
    val returned: Int = 12,
    val collectionURI: String = "http://gateway.marvel.com/v1/public/characters/1011334/comics",
    val items: List<MockComicSumary> = listOf(MockComicSumary())
)

data class MockComicSumary(
    val resourceURI: String = "http://gateway.marvel.com/v1/public/comics/21366",
    val name: String = "Avengers: The Initiative (2007) #14)",
)
