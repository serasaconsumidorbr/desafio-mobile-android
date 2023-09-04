package com.example.marvel_app.framework.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.marvel_app.framework.db.AppDatabase
import com.example.marvel_app.framework.db.entity.CharacterEntity
import com.example.marvel_app.framework.db.entity.RemoteKey
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val query: String,
    private val database: AppDatabase,
    private val remoteDataSource: CharactersRemoteDatasource
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = database.characterDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }

                    if (remoteKey.nextOffset == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextOffset
                }
            }

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val characterPaging = remoteDataSource.fetchCharacters(queries)

            val responseOffset = characterPaging.offset
            val totalCharacters = characterPaging.total

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearAll()
                    characterDao.clearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(nextOffset = responseOffset + state.config.pageSize)
                )

                val characterEntity = characterPaging.character.map {
                    CharacterEntity(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl
                    )
                }

                characterDao.insertAll(characterEntity)
            }

            MediatorResult.Success(endOfPaginationReached = responseOffset >= totalCharacters)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}