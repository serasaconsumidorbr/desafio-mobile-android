package com.br.leandro.marvel_hero_app.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: Hero)

    @Delete
    suspend fun delete(hero: Hero)

    @Query("SELECT * FROM ${Hero.TABLE_NAME}")
    fun getFavoriteHeroes(): Flow<List<Hero>>

}