package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.data.local.CharactersDao
import com.example.marvel_characters.data.remote.HTTPRequest
import com.example.marvel_characters.data.remote.MarvelAPI
import com.example.marvel_characters.domain.converters.CharactersConverter
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.service.CheckNetworkConnection

class CharactersRepositoryImpl(
    private val api: MarvelAPI,
    private val db: CharactersDao,
    private val connection: CheckNetworkConnection
    ) : CharactersRepository {

    override suspend fun getCharacters(): Pair<List<Characters>, Boolean> {
        val isNetworkAvailable = connection.isNetworkAvailable()

        return if(isNetworkAvailable){
            try {
                getCharactersFromAPIToDatabase()
                Pair(getCharactersFromDatabase(), true)
            } catch (e: Exception){
                print(e.toString())
                Pair(getCharactersFromDatabase(), false)
            }
        } else {
            Pair(getCharactersFromDatabase(), false)
        }
    }

    private suspend fun getCharactersFromAPIToDatabase(){
        api.getCharacters(
            apikey = HTTPRequest.PUBLIC_KEY,
            timestamp = HTTPRequest.ts.toString(),
            hash = HTTPRequest.hash
        ).data.results.forEach {
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