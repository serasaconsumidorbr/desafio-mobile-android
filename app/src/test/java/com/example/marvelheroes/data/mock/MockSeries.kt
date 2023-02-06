package com.example.marvelheroes.data.mock

data class MockSeries(
    val available: Int =3,
    val returned: Int =3,
    val collectionURI: String ="http://gateway.marvel.com/v1/public/characters/1011334/series",
    val items : List<MockSeriesSummary> = listOf(MockSeriesSummary())
)

data class MockSeriesSummary(
    val resourceURI: String ="http://gateway.marvel.com/v1/public/series/1945",
    val name: String = "Avengers: The Initiative (2007 - 2010))",
)
