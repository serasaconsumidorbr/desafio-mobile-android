package com.example.domain.heroes.usecase

import com.example.domain.heroes.model.Character
import com.example.domain.heroes.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke(page : Int) : Flow<List<Character>> = repository.getCharacters(page)
}