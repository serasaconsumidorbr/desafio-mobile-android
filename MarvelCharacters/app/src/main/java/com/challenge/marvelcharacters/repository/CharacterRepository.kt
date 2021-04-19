package com.challenge.marvelcharacters.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.challenge.marvelcharacters.dataSource.CharactersDataSource
import com.challenge.marvelcharacters.model.Character
import com.challenge.marvelcharacters.network.CharacterInterface
import com.challenge.marvelcharacters.network.RetrofitInitializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class CharacterRepository {

    private val service = RetrofitInitializer.buildService(CharacterInterface::class.java)

   suspend fun loadCharacters(count : Int) : List<Character> {
        var response :  List<Character> = listOf()
        withContext(Dispatchers.IO){
            try {
                response = service.list(0,count).body()?.data?.result ?: listOf()
            }catch (cause : Throwable){
                //TODO
            }
        }
        return response
    }

    fun getCharacters() : Flow<PagingData<Character>>{
        return Pager(
            config =  PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CharactersDataSource(service)}

        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}