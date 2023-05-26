package com.victorvgc.mymarvelheros.home.di

import androidx.room.Room
import com.victorvgc.mymarvelheros.home.data.database.MarvelDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeModule = module {
    single {
        val db = Room.databaseBuilder(
            androidContext(),
            MarvelDatabase::class.java,
            MarvelDatabase.name
        ).build()

        db.heroDao()
    }
}