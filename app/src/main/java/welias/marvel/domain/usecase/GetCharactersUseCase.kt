package welias.marvel.domain.usecase

import kotlinx.coroutines.flow.Flow
import welias.marvel.domain.model.CharacterDomain
import welias.marvel.domain.repository.AppRepository

class GetCharactersUseCase(private val repository: AppRepository) {
    fun execute(offset: Int): Flow<List<CharacterDomain>> {
        return repository.getListCharacters(offset)
    }
}
