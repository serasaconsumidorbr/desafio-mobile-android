package com.cajusoftware.marvelcharacters.data.database.dao

import androidx.room.*
import com.cajusoftware.marvelcharacters.BuildConfig
import com.cajusoftware.marvelcharacters.data.database.dtos.CharacterDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<CharacterDto>)

    @Query("SELECT * FROM character ORDER BY RANDOM() LIMIT :limit")
    fun getCharactersRandomly(limit: Int = 5): List<CharacterDto>

    @Query("SELECT * from character ORDER BY name LIMIT :limit OFFSET :pagingNumber")
    suspend fun getCharactersForPaging(
        pagingNumber: Int,
        limit: Int = BuildConfig.PAGE_SIZE
    ): List<CharacterDto>

    @Query("SELECT * from character WHERE id = :characterId")
    fun getCharacter(characterId: Int): Flow<CharacterDto?>

    @Query("SELECT COUNT(id) FROM character")
    suspend fun getCount(): Int
}