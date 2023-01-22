package com.example.data.heroes.local.dao

import androidx.room.*
import com.example.data.heroes.local.entity.HeroEntity

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: List<HeroEntity>)

    @Query("Select * From Hero")
    suspend fun getAll() : List<HeroEntity>

    @Query("Delete from Hero")
    @Transaction
    suspend fun deleteAll()

    @Query("Select * From Hero LIMIT :offset")
    suspend fun getPagedList(offset: Int) : List<HeroEntity>


}