package com.example.home_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterHomeCarouselDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(
        characters: List<CharacterHomeEntity>
    )

    @Query("DELETE FROM characterhomeentity")
    suspend fun clearCharacters()

    @Query("SELECT * FROM characterhomeentity")
    suspend fun getCharacters(): List<CharacterHomeEntity>
}