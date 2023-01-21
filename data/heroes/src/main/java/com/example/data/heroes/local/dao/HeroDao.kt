package com.example.data.heroes.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.heroes.local.entity.HeroEntity

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monsters: List<HeroEntity>)

    @Query("Delete from Hero")
    suspend fun deleteAll()

    @Query("Select * From Hero LIMIT :limit OFFSET :offset")
    suspend fun getPagedList( offset: Int, limit: Int) : List<HeroEntity>


}