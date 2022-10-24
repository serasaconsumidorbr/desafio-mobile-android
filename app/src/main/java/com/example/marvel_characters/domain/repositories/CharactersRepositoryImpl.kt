package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.data.local.CharactersDao
import com.example.marvel_characters.data.remote.HTTPRequest
import com.example.marvel_characters.data.remote.MarvelAPI
import com.example.marvel_characters.domain.converters.CharactersConverter
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.service.CheckNetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val api: MarvelAPI,
    private val db: CharactersDao,
    private val connection: CheckNetworkConnection
    ) : CharactersRepository {

    override suspend fun getCharacters(): Pair<List<Characters>, Boolean> = coroutineScope {
        connection.isNetworkAvailable()
        if(connection.isNetworkAvailable()){
            try {
                withContext(Dispatchers.Default) {
                    db.deleteCharacters()
                    insertCharactersInDatabase(getListCharactersFromAPI())
                }
                Pair(withContext(Dispatchers.Default) { getCharactersFromDatabase() }, true)
            } catch (e: Exception){
                print(e.toString())
                Pair(withContext(Dispatchers.Default) { getCharactersFromDatabase() }, false)
            }
        } else {
            Pair(withContext(Dispatchers.Default) { getCharactersFromDatabase() }, false)
        }
    }

    private suspend fun getListCharactersFromAPI(): List<Characters>{
        val resultFromAPI = api.getCharacters(
            apikey = HTTPRequest.PUBLIC_KEY,
            timestamp = HTTPRequest.ts.toString(),
            hash = HTTPRequest.hash
        )
        return resultFromAPI.data.results
    }

    private suspend fun insertCharactersInDatabase(charactersList: List<Characters>){
        charactersList.forEach {
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

    override suspend fun loadNewCharacters(): List<Characters> {
        val resultFromAPI = arrayListOf<Characters>()
        if(connection.isNetworkAvailable()){
            resultFromAPI.addAll(getListCharactersFromAPI())
            insertCharactersInDatabase(resultFromAPI)
        }
        return resultFromAPI
    }
}