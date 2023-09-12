package com.example.marvel_characters.database

import android.content.Context
import androidx.room.*
import com.example.marvel_characters.database.entities.DatabaseCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Query("SELECT * FROM Character")
    fun observeCharacterList(): Flow<List<DatabaseCharacter>>

    @Query("SELECT * FROM Character WHERE id = :id")
    fun observeCharacterById(id: String): Flow<DatabaseCharacter>

    @Query("SELECT * FROM Character")
    suspend fun getCharactersList(): List<DatabaseCharacter>

    @Query("SELECT * FROM Character WHERE id = :id")
    suspend fun getCharacterById(id: String): DatabaseCharacter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DatabaseCharacter)

    @Update
    suspend fun updateCharacter(character: DatabaseCharacter)

    @Query("DELETE FROM Character WHERE id = :id")
    suspend fun deleteCharacterById(id: String)

    @Query("DELETE FROM Character")
    suspend fun deleteCharacter()
}

@Database(entities = [DatabaseCharacter::class], version = 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {
    abstract val marvelDao: MarvelDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null
        fun getInstance(context: Context): CharacterDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    CharacterDatabase::class.java, "character.db"
                ).build().also {
                    INSTANCE = it
                }

            }
        }
    }
}


