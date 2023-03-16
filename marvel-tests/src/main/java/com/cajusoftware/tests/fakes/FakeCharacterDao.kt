package com.cajusoftware.tests.fakes

import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.dtos.CharacterDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterDao : CharacterDao {

    private val characterList: MutableList<CharacterDto> = mutableListOf()

    override suspend fun insertCharacters(characters: List<CharacterDto>) {
        characterList.addAll(characters)
    }

    override fun getCharactersRandomly(limit: Int): List<CharacterDto> {
        return characterList.asSequence().shuffled().take(limit).toList()
    }

    override suspend fun getCharactersForPaging(pagingNumber: Int, limit: Int): List<CharacterDto> {
        return characterList.filter { it.id >= pagingNumber }.take(limit)
    }

    override fun getCharacter(characterId: Int): Flow<CharacterDto?> {
        return flow { emit(characterList.first { it.id == characterId }) }
    }

    override suspend fun getCount(): Int {
        return characterList.size
    }

    fun clear() {
        characterList.clear()
    }
}