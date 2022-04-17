package com.example.home_data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_domain.repository.HomeListRepository
import com.example.home_domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeListRepositoryImpl @Inject constructor(
    private val homeListDataSource: HomeListDataSource,
) : HomeListRepository {

    override fun getCharactersList(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 500),
        pagingSourceFactory = { homeListDataSource }
    ).flow
}