package com.example.data.heroes

import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.mapper.toDomain
import com.example.data.heroes.local.mapper.toEntity
import com.example.data.heroes.remote.HeroesApi
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.model.Page
import com.example.domain.heroes.repository.HeroRepository
import com.example.utils.networkCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class HeroRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: HeroesApi,
    private val localDataSource: HeroDao,
    val dispatcher: CoroutineDispatcher
) : HeroRepository {

    private val limit = 20
    private var totalCount = 0

    override fun getHeroes(page: Int): Flow<Page> = flow {

        var offset =
            if (totalCount > limit * page || totalCount == 0) limit * page else totalCount - limit

        networkCall {
            remoteDataSource.getCharacters(offset = offset, limit = 20)
        }.flowOn(dispatcher).catch {

            offset =
                if (totalCount > limit * page || totalCount == 0) limit * (page + 1) else totalCount - limit
            val list = localDataSource.getPagedList(offset = offset)
            totalCount = localDataSource.getAll().size
            if (list.isEmpty())
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
            if (page == 0) {
                localDataSource.delete(localDataSource.getAll())
            }
            totalCount = it.data.total.toInt()
            it.data.results.toEntity().forEach {
                localDataSource.insert(it)
            }
            val list =
                localDataSource.getAll().sortedBy { it.name }
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