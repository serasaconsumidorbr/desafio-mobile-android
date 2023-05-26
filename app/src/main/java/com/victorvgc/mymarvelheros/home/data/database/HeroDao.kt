package com.victorvgc.mymarvelheros.home.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.victorvgc.mymarvelheros.home.data.model.local.LocalHero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: LocalHero)

    @Query("SELECT * FROM hero WHERE `offset` = 0")
    suspend fun getInitialHeroes(): List<LocalHero>

    @Query("SELECT * FROM hero WHERE `offset` = :offset")
    suspend fun getHeroPage(offset: Int): List<LocalHero>
}