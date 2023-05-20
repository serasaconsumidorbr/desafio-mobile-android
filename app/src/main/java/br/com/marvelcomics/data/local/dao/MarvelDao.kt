package br.com.marvelcomics.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import br.com.marvelcomics.data.dto.MarvelCharLocal

@Dao
interface MarvelDao {

    @Upsert
    suspend fun upsertAll(chars: List<MarvelCharLocal>)

    @Query("SELECT * FROM marvel_character ORDER BY name")
    suspend fun getMarvelCharacters(): List<MarvelCharLocal>

    @Query("DELETE FROM marvel_character")
    suspend fun clearAll()
}