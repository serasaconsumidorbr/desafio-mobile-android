package com.example.marvelheroes.data.mock

import java.io.Serializable

data class MockEvents(
    val available: Int =0,
    val returned: Int =0,
    val collectionURI: String ="http://gateway.marvel.com/v1/public/characters/1016823/events",
    val items: List<MockEventSummary> = listOf(MockEventSummary())
)

data class MockEventSummary(
    val resourceURI: String = "http://gateway.marvel.com/v1/public/characters/1016823/events",
    val name: String = "Teste funcionando",
) : Serializable
