package com.example.marvel_characters.data.repository

import com.example.marvel_characters.core.network.MarvelService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MarvelCharacterRepository @Inject constructor(
    private val service: MarvelService,
) : CharacterRepository {
    private var total: Int? = null

    override fun retrieveCharacters(page: Int): Single<CharacterRepository.Response> {
        val offset = page * LIMIT
        total?.let {
            if (page * LIMIT >= it) {
                return Single.just(CharacterRepository.Response.EndOfList)
            }
        }

        return service.getCharacters(limit = LIMIT, offset = offset)
            .doOnSuccess { total = it.data?.total ?: 0 }
            .map<CharacterRepository.Response> {
                CharacterRepository.Response.Success(it.data?.results.orEmpty())
            }
            .onErrorReturn { CharacterRepository.Response.Error }

    }

    companion object {
        private const val LIMIT = 20
    }
}