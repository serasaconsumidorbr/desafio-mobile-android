package com.br.leandro.marvel_hero_app.datasource.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class CharacterEntity (

    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String
)