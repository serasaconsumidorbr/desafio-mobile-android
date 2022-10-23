package com.example.home_data.remote.datasource

import androidx.paging.PagingSource
import com.example.home_domain.model.CharacterHomeUiModel

abstract class HomeListDataSource : PagingSource<Int, CharacterHomeUiModel>()