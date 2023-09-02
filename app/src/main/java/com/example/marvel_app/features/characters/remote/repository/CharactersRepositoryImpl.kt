package com.example.marvel_app.features.characters.remote.repository

import androidx.paging.PagingSource
import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor (
    private val charactersRemoteDatasource: CharactersRemoteDatasource
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(charactersRemoteDatasource, query)
    }
}