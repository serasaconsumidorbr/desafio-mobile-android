package br.com.marvelcomics.data.repository

import br.com.marvelcomics.R
import br.com.marvelcomics.base.util.Resource
import br.com.marvelcomics.base.util.UiException
import br.com.marvelcomics.data.local.dao.MarvelDao
import br.com.marvelcomics.data.mapper.toMarvelCharLocal
import br.com.marvelcomics.data.mapper.toMarvelCharacter
import br.com.marvelcomics.data.remote.MarvelApi
import br.com.marvelcomics.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException

interface MarvelCharRepository {
    fun fetchCharacters(offset: Int): Flow<Resource<List<MarvelCharacter>>>
}

class MarvelCharRepositoryImpl(
    private val marvelApi: MarvelApi,
    private val dao: MarvelDao,
) : MarvelCharRepository {

    companion object {
        private const val START_OFFSET = 0
    }

    override fun fetchCharacters(offset: Int): Flow<Resource<List<MarvelCharacter>>> = flow {
        emit(Resource.Loading())
        if (offset == START_OFFSET) {
            val local = dao.getMarvelCharacters()
            if (local.isNotEmpty()) {
                emit(Resource.Success(local.map { it.toMarvelCharacter() }))
                return@flow
            }
        }

        val response = marvelApi.fetchHeroes(offset = offset)
        dao.upsertAll(response.results.map { it.toMarvelCharLocal() })
        emit(Resource.Success(response.results.map { it.toMarvelCharacter() }))
    }.catch {
        when (it) {
            is SocketTimeoutException -> emit(Resource.Error(UiException.TimeoutException()))
            is HttpException -> emit(Resource.Error(UiException.GenericApiException()))
            else -> emit(Resource.Error(UiException.GenericUiException()))
        }
    }


}