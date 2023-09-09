package com.desafio.marvelapp.framework

import androidx.paging.PagingSource
import androidx.viewpager2.widget.ViewPager2.OffscreenPageLimit
import com.desafio.marvelapp.framework.network.response.DataWrapperResponse
import com.desafio.marvelapp.framework.network.response.toCharacterModel
import com.desafio.marvelapp.framework.paging.CharactersPagingSource
import com.project.core.data.repository.ICharactersRemoteDataSource
import com.project.core.data.repository.ICharactersRepository
import com.project.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: ICharactersRemoteDataSource<DataWrapperResponse>
): ICharactersRepository {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override suspend fun getCarouselCharacters(limit: Int): List<Character> {
        val queries = hashMapOf("limit" to limit.toString())
        val response = remoteDataSource.fetchCharacters(queries)
        return response.data.results.map {
            it.toCharacterModel()
        }
    }
}