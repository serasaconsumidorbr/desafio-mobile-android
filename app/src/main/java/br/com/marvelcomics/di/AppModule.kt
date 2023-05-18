package br.com.marvelcomics.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.marvelcomics.data.remote.MarvelApi
import br.com.marvelcomics.data.remote.paging.MarvelCharacterPagingSource
import br.com.marvelcomics.feature.HomeViewModel
import br.com.marvelcomics.model.MarvelCharacter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PAGING_SIZE = 20

object AppModule {

    val dependencies = module {
        single { providePager(get()) }

        viewModel { HomeViewModel(get()) }
    }

    private fun providePager(marvelApi: MarvelApi): Pager<Int, MarvelCharacter> {
        return Pager(
            config = PagingConfig(pageSize = PAGING_SIZE),
            pagingSourceFactory = { MarvelCharacterPagingSource(marvelApi) }
        )
    }
}