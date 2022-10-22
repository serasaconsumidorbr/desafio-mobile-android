package com.example.marvel_characters.di

import com.example.marvel_characters.data.local.CharactersDao
import com.example.marvel_characters.data.local.MarvelRoomDatabase
import com.example.marvel_characters.data.remote.MarvelAPI
import com.example.marvel_characters.data.remote.RetrofitBuilder
import com.example.marvel_characters.domain.repositories.CharactersRepository
import com.example.marvel_characters.domain.repositories.CharactersRepositoryImpl
import com.example.marvel_characters.presentation.catalog.CatalogViewModel
import com.example.marvel_characters.service.CheckNetworkConnection
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val appModule = module {

        single<MarvelAPI> { RetrofitBuilder.marvelAPI }
        single<CharactersDao> {
            MarvelRoomDatabase.getMarvelRoomDatabase(androidContext()).charactersDao()
        }
        single { CheckNetworkConnection(androidContext()) }

        factory<CharactersRepository> { CharactersRepositoryImpl(get(), get(), get()) }

        viewModel {
            CatalogViewModel(get())
        }
    }
}