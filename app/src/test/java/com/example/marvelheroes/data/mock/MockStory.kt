package com.example.marvelheroes.data.mock

data class MockStory(
    val available: Int = 21,
    val returned: Int = 20,
    val collectionURI: String = "http://gateway.marvel.com/v1/public/characters/1011334/stories",
    val items: List<MockStorySummary> = listOf(MockStorySummary())
)

data class MockStorySummary(
    val resourceURI: String ="http://gateway.marvel.com/v1/public/stories/19947",
    val name: String ="Cover #19947",
    val type: String ="cover"
)
