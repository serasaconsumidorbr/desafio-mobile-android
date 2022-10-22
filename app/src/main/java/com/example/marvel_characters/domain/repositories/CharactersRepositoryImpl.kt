package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.data.local.CharactersDao
import com.example.marvel_characters.data.remote.HTTPRequest
import com.example.marvel_characters.data.remote.MarvelAPI
import com.example.marvel_characters.domain.converters.CharactersConverter
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.service.CheckNetworkConnection
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class CharactersRepositoryImpl(
    private val api: MarvelAPI,
    private val db: CharactersDao,
    private val connection: CheckNetworkConnection
    ) : CharactersRepository {

    override suspend fun getCharacters(): Pair<List<Characters>, Boolean> = coroutineScope {
        val isNetworkAvailable = connection.isNetworkAvailable()
        if(isNetworkAvailable){
            try {
                async { db.deleteCharacters() }.await()
                async { getCharactersFromAPIToDatabase() }.await()
                Pair(async { getCharactersFromDatabase() }.await(), true)
            } catch (e: Exception){
                print(e.toString())
                Pair(async { getCharactersFromDatabase() }.await(), false)
            }
        } else {
            Pair(async { getCharactersFromDatabase() }.await(), false)
        }
    }

    private suspend fun getCharactersFromAPIToDatabase() {
        val resultFromAPI = api.getCharacters(
            apikey = HTTPRequest.PUBLIC_KEY,
            timestamp = HTTPRequest.ts.toString(),
            hash = HTTPRequest.hash
        )
        resultFromAPI.data.results.forEach {
            try {
                db.insert(CharactersConverter.toEntity(it))
            } catch (e: Exception){
                print(e.toString())
            }
        }
    }

    private suspend fun getCharactersFromDatabase(): List<Characters> {
        val listResult = arrayListOf<Characters>()
        db.getCharacters().forEach {
            listResult.add(CharactersConverter.fromEntity(it))
        }
        return listResult
    }
}