package dev.ribeiro.bruno.desafioandroid.domain.usecase

import androidx.paging.PagingData
import dev.ribeiro.bruno.desafioandroid.core.UseCase
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(private val repository: MarvelRepository) : UseCase<Flow<PagingData<CharacterDetail>>>() {

    override suspend fun execute(): Flow<PagingData<CharacterDetail>> = repository.getMarvelCharacters()

}