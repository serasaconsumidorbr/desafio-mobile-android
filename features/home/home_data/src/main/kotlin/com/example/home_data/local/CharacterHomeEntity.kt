package com.example.home_data.local

import androidx.room.Entity

@Entity
data class CharacterHomeEntity(
    val name: String,
    val description: String,
    val imageUrl: String
)
