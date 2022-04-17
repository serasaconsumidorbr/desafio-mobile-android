package com.example.home_data.remote.datasource

import androidx.paging.PagingSource
import com.example.home_domain.model.Character

abstract class HomeListDataSource: PagingSource<Int, Character>()