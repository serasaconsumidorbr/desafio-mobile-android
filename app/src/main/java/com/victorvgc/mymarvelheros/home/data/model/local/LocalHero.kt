package com.victorvgc.mymarvelheros.home.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.victorvgc.mymarvelheros.home.domain.domain.Hero

@Entity(tableName = "hero")
data class LocalHero(
    @PrimaryKey
    val id: Int,
    val offset: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?
) {

    companion object {
        fun fromModel(hero: Hero, offset: Int = 0): LocalHero {
            return LocalHero(
                id = hero.id,
                offset = offset,
                name = hero.name,
                imageUrl = hero.imageUrl
            )
        }
    }

    fun toModel(): Hero {
        return Hero(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
    }
}