package com.example.marvel_characters

import com.example.marvel_characters.domain.MarvelCharacter

object Samples {
    val marvelCharacterFirst = MarvelCharacter(
        id = "1011031",
        name = "Agent X (Nijo)",
        description = "Originally a partner of the mind-altering assassin Black Swan, Nijo spied on Deadpool as part of the Swan's plan to exact revenge for Deadpool falsely taking credit for the Swan's assassination of the Four Winds crime family, which included Nijo's brother.",
        thumbnailUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
    )

    val marvelCharacterSecond = MarvelCharacter(
        id = "1009144",
        name = "A.I.M.",
        description = "AIM is a terrorist organization bent on destroying the world.",
        thumbnailUrl = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"
    )
    val marvelCharactersList = listOf(marvelCharacterFirst, marvelCharacterSecond)
}