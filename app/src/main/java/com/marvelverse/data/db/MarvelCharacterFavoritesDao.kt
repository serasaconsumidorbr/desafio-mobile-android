package com.marvelverse.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MarvelCharacterFavoritesDao {
    @Query("SELECT * FROM MarvelCharacterEntity")
    fun getAll(): LiveData<List<MarvelCharacterEntity>>

    @Query("SELECT * FROM MarvelCharacterEntity WHERE character_name LIKE :characterName LIMIT 1")
    fun findByName(characterName: String): MarvelCharacterEntity?

    @Insert
    fun insert(character: MarvelCharacterEntity)

    @Insert
    fun insertAll(vararg character: MarvelCharacterEntity)

    @Query("DELETE FROM MarvelCharacterEntity WHERE character_name LIKE :characterName")
    fun deleteBy(characterName: String)
}