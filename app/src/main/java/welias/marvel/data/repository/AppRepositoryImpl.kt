package welias.marvel.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import welias.marvel.data.datasource.remote.AppRemoteDataSource
import welias.marvel.domain.mapper.toCharacterDomainItem
import welias.marvel.domain.model.CharacterDomain
import welias.marvel.domain.repository.AppRepository

class AppRepositoryImpl(
    private val remoteDataSource: AppRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : AppRepository {

    override fun getListCharacters(offset: Int): Flow<List<CharacterDomain>> {
        return flow {
            remoteDataSource.getListCharacters(offset).collect { characters ->
                emit(
                    characters.map {
                        it.toCharacterDomainItem()
                    }
                )
            }
        }.flowOn(dispatcher)
    }
}
