package com.br.leandro.marvel_hero_app.di

import com.br.leandro.marvel_hero_app.datasource.db.AppDatabase
import com.br.leandro.marvel_hero_app.datasource.IMarvelRepository
import com.br.leandro.marvel_hero_app.datasource.MarvelRepository
import com.br.leandro.marvel_hero_app.datasource.network.MarvelAPIFactory
import com.br.leandro.marvel_hero_app.datasource.network.MarvelApi
import com.br.leandro.marvel_hero_app.ui.viewmodel.activity.SharedViewModel
import com.br.leandro.marvel_hero_app.ui.viewmodel.fragment.DetailViewModel
import com.br.leandro.marvel_hero_app.ui.viewmodel.fragment.RootViewModel
import org.koin.dsl.module

val networkModule = module {
    factory { MarvelAPIFactory.marvelApi() }
}

val databaseModule = module {
    factory { AppDatabase.getInstance(get()) }
    single<IMarvelRepository> {
        MarvelRepository(
            get(),
            get()
        )
    }
}

val viewModelModule = module {
    single { RootViewModel(get()) }
    single { DetailViewModel(get()) }
    single { SharedViewModel() }
}

val repositoryModule = module {
    fun provideMarvelRepository(database: AppDatabase, api: MarvelApi): MarvelRepository {
        return MarvelRepository(database, api)
    }

    single { provideMarvelRepository(get(), get()) }
}