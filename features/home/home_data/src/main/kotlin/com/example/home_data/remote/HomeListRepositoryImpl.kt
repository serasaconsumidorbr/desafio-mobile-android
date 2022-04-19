package com.example.home_data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_data.remote.datasource.HomeListDataSourceImpl
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.Character
import com.example.home_domain.repository.HomeListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeListRepositoryImpl @Inject constructor(
    private val homeListDataSource: HomeListDataSource,
    private val pageSize: Int,
) : HomeListRepository {

    override fun getCharactersList(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = { homeListDataSource }
    ).flow
}