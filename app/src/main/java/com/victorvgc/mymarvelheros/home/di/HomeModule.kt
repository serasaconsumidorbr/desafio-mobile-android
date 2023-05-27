package com.victorvgc.mymarvelheros.home.di

import androidx.room.Room
import com.victorvgc.mymarvelheros.core.utils.getApi
import com.victorvgc.mymarvelheros.home.data.data_source.LocalHeroesDataSourceImpl
import com.victorvgc.mymarvelheros.home.data.data_source.RemoteHeroesDataSourceImpl
import com.victorvgc.mymarvelheros.home.data.database.MarvelDatabase
import com.victorvgc.mymarvelheros.home.data.repository.HeroesRepositoryImpl
import com.victorvgc.mymarvelheros.home.data.service.HeroesService
import com.victorvgc.mymarvelheros.home.data.utils.DateUtils
import com.victorvgc.mymarvelheros.home.data.utils.HashUtils
import com.victorvgc.mymarvelheros.home.domain.data_source.HeroesDataSource
import com.victorvgc.mymarvelheros.home.domain.repository.HeroesRepository
import com.victorvgc.mymarvelheros.home.domain.use_case.GetHeroesPage
import com.victorvgc.mymarvelheros.home.domain.use_case.GetInitialHeroes
import com.victorvgc.mymarvelheros.home.ui.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    // remote
    single { getApi(HeroesService::class.java) }

    // local
    single {
        val db = Room.databaseBuilder(
            androidContext(),
            MarvelDatabase::class.java,
            MarvelDatabase.name
        ).build()

        db.heroDao()
    }

    // utils
    single { DateUtils() }
    single { HashUtils() }

    // data source
    single<HeroesDataSource.Remote> {
        RemoteHeroesDataSourceImpl(get(), get(), get())
    }
    single<HeroesDataSource.Local> { LocalHeroesDataSourceImpl(get()) }

    // repository
    single<HeroesRepository> { HeroesRepositoryImpl(get(), get()) }

    // use case
    factory { GetInitialHeroes(get()) }
    factory { GetHeroesPage(get()) }

    // view model

    viewModel {
        HomeViewModel(get(), get())
    }
}