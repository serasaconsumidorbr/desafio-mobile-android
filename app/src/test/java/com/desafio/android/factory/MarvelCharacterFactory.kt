package com.desafio.android.factory

import com.desafio.android.domain.entity.MarvelCharacter

object MarvelCharacterFactory {
    fun getCharacters(size: Int): List<MarvelCharacter> {
        return mutableListOf<MarvelCharacter>().apply {
            for (i in 0 until size) add(MarvelCharacter.getMockedCharacter())
        }
    }
}