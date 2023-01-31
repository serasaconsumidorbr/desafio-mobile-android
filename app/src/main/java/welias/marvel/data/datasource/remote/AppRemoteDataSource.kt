package welias.marvel.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import welias.marvel.data.model.CharacterResponse

interface AppRemoteDataSource {
    fun getListCharacters(offset: Int): Flow<List<CharacterResponse>>
}
