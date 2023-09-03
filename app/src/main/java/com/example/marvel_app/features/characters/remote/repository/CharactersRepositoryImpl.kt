package com.example.marvel_app.features.characters.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.framework.db.AppDatabase
import com.example.marvel_app.framework.paging.CharactersRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDatasource: CharactersRemoteDatasource,
    private val appDatabase: AppDatabase
) : CharactersRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCachedCharacters(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Character>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = CharactersRemoteMediator(
                query,
                appDatabase,
                charactersRemoteDatasource
            )
        ) {
            appDatabase.characterDao().pagingSource()
        }.flow.map { pagingData ->
            pagingData.map {
                Character(
                    it.id,
                    it.name,
                    it.imageUrl
                )
            }
        }
    }
}