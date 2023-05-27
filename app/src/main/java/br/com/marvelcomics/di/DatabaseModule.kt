package br.com.marvelcomics.di

import android.content.Context
import androidx.room.Room
import br.com.marvelcomics.data.local.dao.MarvelDao
import br.com.marvelcomics.data.local.db.MarvelComicsDatabase
import org.koin.dsl.module

object DatabaseModule {

    private const val DATABASE_NAME = "marvel_heroes_database"

    val dependencies = module {
        single<MarvelComicsDatabase> { provideDatabase(get()) }
        single<MarvelDao> { get<MarvelComicsDatabase>().marvelDao }
    }


    private fun provideDatabase(context: Context): MarvelComicsDatabase =
        Room.databaseBuilder(context, MarvelComicsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

}