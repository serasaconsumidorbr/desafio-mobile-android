package com.example.data.heroes.local.dao

import androidx.room.*
import com.example.data.heroes.local.entity.HeroEntity

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hero: List<HeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: HeroEntity)

    @Query("Select * From Hero")
    suspend fun getAll() : List<HeroEntity>

    @Query("Delete from Hero")
    @Transaction
    suspend fun deleteAll()

    @Delete
    suspend fun delete (hero : List<HeroEntity>)

    @Query("Select * From Hero order by name LIMIT :offset ")
    suspend fun getPagedList(offset: Int) : List<HeroEntity>


}