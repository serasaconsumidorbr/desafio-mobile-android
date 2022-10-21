package com.example.marvel_characters.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvel_characters.domain.models.CharacterEntity

@Dao
interface CharactersDao {

    @Insert
    suspend fun insert(characterEntity: CharacterEntity)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacter(id: Long)

}