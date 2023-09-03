package com.example.marvel_app.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel_app.framework.db.entity.RemoteKey
import com.example.marvel_app.utils.Constants.REMOTE_KEYS_TABLE_NAME

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM ${REMOTE_KEYS_TABLE_NAME}")
    suspend fun remoteKey(): RemoteKey

    @Query("DELETE FROM ${REMOTE_KEYS_TABLE_NAME}")
    suspend fun clearAll()
}