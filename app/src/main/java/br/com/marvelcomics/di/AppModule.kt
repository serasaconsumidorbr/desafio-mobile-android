package br.com.marvelcomics.di

import br.com.marvelcomics.data.repository.MarvelCharRepository
import br.com.marvelcomics.data.repository.MarvelCharRepositoryImpl
import br.com.marvelcomics.feature.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PAGING_SIZE = 20

object AppModule {

    val dependencies = module {
        single<MarvelCharRepository> { MarvelCharRepositoryImpl(get(), get()) }

        viewModel { HomeViewModel(get()) }
    }

}