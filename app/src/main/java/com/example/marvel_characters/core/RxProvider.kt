package com.example.marvel_characters.core

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface RxProvider {
    fun io(): Scheduler
    fun mainThread(): Scheduler

    class Impl @Inject constructor(): RxProvider {
        override fun io(): Scheduler = Schedulers.io()
        override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
    }
}