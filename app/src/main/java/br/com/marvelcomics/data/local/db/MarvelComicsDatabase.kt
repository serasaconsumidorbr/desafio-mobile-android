package br.com.marvelcomics.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.marvelcomics.data.dto.MarvelCharLocal
import br.com.marvelcomics.data.local.dao.MarvelDao

@Database(
    entities = [MarvelCharLocal::class],
    version = 1
)
abstract class MarvelComicsDatabase : RoomDatabase() {

    abstract val marvelDao : MarvelDao
}