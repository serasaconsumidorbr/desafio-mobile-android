package com.example.home_domain.repository

import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow

interface HomeCarouselRepository {
    suspend fun getHomeCarouselCharacters(): List<CharacterHomeUiModel>?
}