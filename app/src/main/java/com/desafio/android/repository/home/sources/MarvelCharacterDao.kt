package com.desafio.android.repository.home.sources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.desafio.android.domain.entity.MarvelCharacter

@Dao
interface MarvelCharacterDao {
    @Query("SELECT * FROM MarvelCharacter")
    suspend fun getAllCharacters(): List<MarvelCharacter>

    @Query("DELETE FROM MarvelCharacter WHERE id = :id")
    suspend fun deleteCharacterById(id: String)

    @Query("DELETE FROM MarvelCharacter")
    suspend fun deleteAllCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<MarvelCharacter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: MarvelCharacter)
}