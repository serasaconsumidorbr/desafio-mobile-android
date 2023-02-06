package com.example.marvelheroes.data.mock

data class MockDataResponse(
    val offset: Int =0,
    val limit: Int =10,
    val total: Int =1562,
    val count: Int =10,
    val results: List<MockCharactersResponse> = listOf(MockCharactersResponse())
)
