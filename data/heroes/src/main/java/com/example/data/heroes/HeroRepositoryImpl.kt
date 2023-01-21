package com.example.data.heroes

import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.mapper.toDomain
import com.example.data.heroes.local.mapper.toEntity
import com.example.data.heroes.remote.HeroesApi
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.model.Page
import com.example.domain.heroes.repository.HeroRepository
import com.example.utils.networkCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HeroRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: HeroesApi,
    private val localDataSource: HeroDao
) : HeroRepository {

    private val limit = 20
    private var totalCount = 0

    override fun getHeroes(page: Int): Flow<Page> = flow {

        val offset = if (totalCount > limit * page || totalCount == 0) limit * page else totalCount - limit

        networkCall {
            remoteDataSource.getCharacters(offset = offset, limit = 20)
        }.catch {

            val list = localDataSource.getPagedList(offset = offset, limit = 20)
            if(list.isEmpty())
                throw Exception("")
            else
            emit(
                Page(
                    page = page,
                    nextPage = if (totalCount < limit * page) -1 else page + 1,
                    list.toDomain()
                )
            )
        }.collect {
            totalCount = it.data.total.toInt()
            localDataSource.insert(it.data.results.toEntity())
            val list = localDataSource.getPagedList(offset = offset, limit = 20)
            emit(
                Page(
                    page = page,
                    nextPage = if (totalCount < limit * page) -1 else page + 1,
                    list.toDomain()
                )
            )
        }

    }

}