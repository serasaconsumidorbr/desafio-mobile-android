package com.example.marvel_characters.data.repository

import com.example.marvel_characters.data.dto.CharacterDTO
import com.example.marvel_characters.domain.model.Character
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {
    fun retrieveCharacters(page: Int): Single<Response>

    sealed class Response {
        data class Success(val characters: List<CharacterDTO>): Response()
        object EndOfList: Response()
        object Error: Response()
    }
}