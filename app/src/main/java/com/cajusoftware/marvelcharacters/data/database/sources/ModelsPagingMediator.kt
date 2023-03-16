package com.cajusoftware.marvelcharacters.data.database.sources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.dtos.CharacterDto
import com.cajusoftware.marvelcharacters.data.network.responses.CharacterDataWrapperResponse
import com.cajusoftware.marvelcharacters.data.network.services.MarvelApiService
import com.cajusoftware.marvelcharacters.utils.asCharacterDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ModelsPagingMediator(
    private val characterDao: CharacterDao,
    private val marvelApiService: MarvelApiService
) : RemoteMediator<Int, CharacterDto>() {

    var invalidatePagingSourceCallback: (() -> Unit)? = null

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDto>
    ): MediatorResult {
        return withContext(Dispatchers.IO) {
            try {

                val loadKey = when (loadType) {
                    LoadType.REFRESH -> 0
                    LoadType.PREPEND -> {
                        if (state.isEmpty()) 0
                        else state.lastItemOrNull()?.nextKey
                            ?: return@withContext MediatorResult.Success(
                                endOfPaginationReached = true
                            )
                    }
                    LoadType.APPEND -> {
                        val lastItem = if (state.isEmpty()) 0
                        else state.lastItemOrNull()?.nextKey
                            ?: return@withContext MediatorResult.Success(
                                endOfPaginationReached = true
                            )
                        lastItem
                    }
                }

                val response: CharacterDataWrapperResponse = loadKey.let {
                    marvelApiService.getCharacters(offset = it)
                }

                response.data?.results?.map {
                    it.asCharacterDto(response.copyright, response.data.nextKey)
                }?.let {
                    val count = characterDao.getCount()
                    characterDao.insertCharacters(it)
                    if (loadKey == 0 && count == 0)
                        invalidatePagingSourceCallback?.invoke()
                }

                MediatorResult.Success(
                    endOfPaginationReached = response.data?.hasNext?.not() == true
                )

            } catch (e: IOException) {
                MediatorResult.Error(e)
            } catch (e: HttpException) {
                MediatorResult.Error(e)
            }
        }
    }
}