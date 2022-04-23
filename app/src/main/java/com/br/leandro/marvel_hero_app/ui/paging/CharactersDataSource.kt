package com.br.leandro.marvel_hero_app.ui.paging

import androidx.paging.PageKeyedDataSource
import com.br.leandro.marvel_hero_app.datasource.MarvelRepository
import com.br.leandro.marvel_hero_app.datasource.network.model.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersDataSource(
    private val repository: MarvelRepository,
    private val disposable: CompositeDisposable
) : PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        val numberOfItems = params.requestedLoadSize
        createObservable(0, 1, numberOfItems, callback, null)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Character>
    ) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page, page + 1, numberOfItems, null, callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Character>
    ) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page, page - 1, numberOfItems, null, callback)
    }

    private fun createObservable(
        requestedPage: Int,
        adjacentPage: Int,
        requestedLoadSize: Int,
        initialCallback: LoadInitialCallback<Int, Character>?,
        callback: LoadCallback<Int, Character>?
    ) {
        disposable.add(
            repository.getCharactersApi(requestedPage * requestedLoadSize)
                .subscribe(
                    { response ->
                        initialCallback?.onResult(response.data.results, null, adjacentPage)
                        callback?.onResult(response.data.results, adjacentPage)
                    },
                    { }
                )
        )
    }
}
