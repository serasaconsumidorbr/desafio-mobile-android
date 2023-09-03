package com.example.marvel_app.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel_app.utils.Constants.FAVORITES_COLUMN_INFO_ID
import com.example.marvel_app.utils.Constants.FAVORITES_COLUMN_INFO_IMAGE_URL
import com.example.marvel_app.utils.Constants.FAVORITES_COLUMN_INFO_NAME
import com.example.marvel_app.utils.Constants.FAVORITES_TABLE_NAME

@Entity(tableName = FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)