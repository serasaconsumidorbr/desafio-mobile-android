package com.desafio.marvel.commons.utils

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    protected fun Disposable.handleDisposable(): Disposable =
        apply { disposable.add(this) }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}