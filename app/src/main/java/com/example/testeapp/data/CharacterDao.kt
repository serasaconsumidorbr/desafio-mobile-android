package com.example.testeapp.data

import androidx.room.*
import com.example.testeapp.model.MarvelCharacter

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(marvelCharacter: MarvelCharacter)

    @Query("SELECT * FROM characters order by id asc")
    fun getAll(): List<MarvelCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: Collection<MarvelCharacter>)

    @Query("DELETE FROM characters")
    fun deleteAll()

    @Update
    suspend fun update(character: MarvelCharacter)
}