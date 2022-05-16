package com.updeveloped.desafiomarvel.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.data.service.MarvelService
import com.updeveloped.desafiomarvel.data.source.PageDataCarouselSource
import com.updeveloped.desafiomarvel.data.source.PageDataSource
import com.updeveloped.desafiomarvel.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class MarvelRepositoryImpl(private val marvelService: MarvelService): MarvelRepository {
    override suspend fun getMarvelCharactersPage(): Flow<PagingData<CharacterDetail>> {
        return try {
            Pager(PagingConfig(pageSize = 20)) {
                PageDataSource(marvelService)
            }.flow
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getMarvelCharacters(): List<CharacterDetail> {
        return try {
            PageDataCarouselSource(marvelService).load()
        }catch (e: Exception){
            throw e
        }
    }
}
