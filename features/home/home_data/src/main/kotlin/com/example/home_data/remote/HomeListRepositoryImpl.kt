package com.example.home_data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.home_data.remote.datasource.HomeListDataSourceImpl
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.Character
import com.example.home_domain.repository.HomeListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeListRepositoryImpl @Inject constructor(
    private val api: HomeListApi,
    private val mapper: CharactersDataDtoToCharactersMapper,
) : HomeListRepository {

    companion object {
        const val PAGE_SIZE = 20
        private const val MAX_SIZE = 500
    }

    override fun getCharactersList(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = MAX_SIZE
        ),
        pagingSourceFactory = {
            HomeListDataSourceImpl(
                api = api,
                mapper = mapper
            )
        }
    ).flow
}