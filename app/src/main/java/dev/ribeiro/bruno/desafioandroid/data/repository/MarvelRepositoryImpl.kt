package dev.ribeiro.bruno.desafioandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.ribeiro.bruno.desafioandroid.data.service.MarvelService
import dev.ribeiro.bruno.desafioandroid.data.source.PageDataSource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class MarvelRepositoryImpl(
    private val marvelService: MarvelService
): MarvelRepository {

    override suspend fun getMarvelCharacters(): Flow<PagingData<CharacterDetail>> {
        return try {
            Pager(PagingConfig(pageSize = 20)) {
                PageDataSource(marvelService)
            }.flow
        }catch (e: Exception){
            throw e
        }
    }

}