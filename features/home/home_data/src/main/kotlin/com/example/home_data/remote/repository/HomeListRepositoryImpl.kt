package com.example.home_data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.repository.HomeListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeListRepositoryImpl @Inject constructor(
    private val homeListDataSource: HomeListDataSource,
    private val pageSize: Int,
) : HomeListRepository {

    override fun getCharactersList(): Pager<Int, CharacterHomeUiModel> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = { homeListDataSource }
    )
}