package com.victorvgc.mymarvelheros.home.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.victorvgc.mymarvelheros.home.domain.domain.Hero

@Entity(tableName = "hero")
class LocalHero(
    @PrimaryKey
    val id: Int,
    val offset: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
) {
    fun toModel(): Hero {
        return Hero(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
    }
}