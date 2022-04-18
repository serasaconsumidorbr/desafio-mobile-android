package com.example.home_data.remote.datasource

import androidx.paging.PagingSource
import com.example.home_domain.model.Character

interface HomeListDataSource {
    fun factoryGenerator(): PagingSource<Int, Character>
}