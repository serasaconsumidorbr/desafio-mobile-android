package com.example.marvelheroes.data.mock

import com.example.marvelheroes.domain.models.characters.Event
import com.example.marvelheroes.domain.models.characters.Series
import com.example.marvelheroes.domain.models.characters.Story

data class MockCharactersResponse(
    val id: Int =1011334,
    val name: String ="3-D Man",
    val description: String ="",
    val modified: String ="Tue Apr 29 15:18:17 GMT-03:00 2014",
    val resourceURI: String ="http://gateway.marvel.com/v1/public/characters/1011334",
    val urls: List<MockUrlsResponse> = listOf(MockUrlsResponse()),
    val thumbnail: MockImage = MockImage(),
    val comics: MockComics = MockComics(),
    val stories: List<MockStory> = listOf(MockStory()),
    val events: List<MockEvents> = listOf(MockEvents()),
    val series: List<MockSeries> = listOf(MockSeries())
)
