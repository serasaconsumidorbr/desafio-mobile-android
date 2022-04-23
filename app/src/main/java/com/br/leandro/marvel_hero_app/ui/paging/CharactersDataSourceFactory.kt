package com.br.leandro.marvel_hero_app.ui.paging

import androidx.paging.DataSource
import com.br.leandro.marvel_hero_app.datasource.MarvelRepository
import com.br.leandro.marvel_hero_app.datasource.network.model.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersDataSourceFactory(
    private val repository: MarvelRepository,
    private val disposable: CompositeDisposable
) :
    DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
        return CharactersDataSource(repository, disposable)
    }
}
