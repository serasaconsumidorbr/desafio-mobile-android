package com.desafio.android.repository.home

import android.util.Log
import com.desafio.android.BuildConfig
import com.desafio.android.core.base.storage.InternalDatabase
import com.desafio.android.core.extension.toTimestamp
import com.desafio.android.core.standalone.getMD5
import com.desafio.android.domain.entity.MarvelCharacter
import com.desafio.android.repository.home.sources.MarvelCharacterApi
import java.util.*

class HomeRepositoryImpl(
    private val api: MarvelCharacterApi,
    private val database: InternalDatabase
) : HomeRepository {
    override suspend fun getMarvelCharacters(
        offset: Int
    ): List<MarvelCharacter> {
        val stored = database.marvelCharacterDao().getAllCharacters()

        val list = if ((offset + 1) > stored.size) {
            val publicKey = BuildConfig.MARVEL_DEVELOPER_API_PUBLIC_KEY
            val privateKey = BuildConfig.MARVEL_DEVELOPER_API_PRIVATE_KEY
            val timestamp = Date().toTimestamp()
            val hash = getMD5(timestamp + privateKey + publicKey)

            api.getMarvelCharacters(
                apikey = publicKey,
                hash = hash,
                timestamp = timestamp,
                offset = offset,
                limit = 25,
                orderBy = "name"
            ).data.results

        } else {
            stored
        }

        database.marvelCharacterDao().addCharacters(list)

        return list
    }
}