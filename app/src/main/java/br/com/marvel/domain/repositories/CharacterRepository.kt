package br.com.marvel.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.marvel.data.MarvelApi
import br.com.marvel.domain.models.Character
import br.com.marvel.domain.sources.CharacterSource
import br.com.marvel.utilities.Constants.Companion.PUBLIC_KEY
import br.com.marvel.utilities.Crypt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val marvelApi: MarvelApi) {

    fun getCharacters(): Flow<PagingData<Character>> {
        val publicKey = PUBLIC_KEY
        val ts = System.currentTimeMillis().toString()

        return Pager(config = PagingConfig(pageSize = 1, maxSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                CharacterSource(
                    ts, publicKey, Crypt().md5Hash(ts, publicKey), marvelApi
                )
            }).flow
    }

}