package welias.marvel.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import welias.marvel.core.exception.mapToCustomError
import welias.marvel.data.model.CharacterResponse
import welias.marvel.data.service.AppService

class AppRemoteDataSourceImpl(private val service: AppService) : AppRemoteDataSource {
    override fun getListCharacters(offset: Int): Flow<List<CharacterResponse>> {
        return flow {
            val response = service.getListCharacters(offset = offset).data.results
            emit(response)
        }.mapToCustomError()
    }
}
