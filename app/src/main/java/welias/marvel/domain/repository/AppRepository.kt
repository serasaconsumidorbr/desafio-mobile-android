package welias.marvel.domain.repository

import kotlinx.coroutines.flow.Flow
import welias.marvel.domain.model.CharacterDomain

interface AppRepository {
    fun getListCharacters(offset: Int): Flow<List<CharacterDomain>>
}
