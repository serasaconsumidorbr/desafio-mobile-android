package br.com.maceda.marvel.data.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "character")
data class Character(
  @PrimaryKey val id: Int,
  @ColumnInfo val name: String,
  @ColumnInfo val description: String,
  val thumbnail: Thumbnail
): Serializable