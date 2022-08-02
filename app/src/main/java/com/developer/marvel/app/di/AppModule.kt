package com.developer.marvel.app.di

import com.developer.marvel.external.network.MarvelAPI
import org.koin.dsl.module

val appModule = module {
    single { MarvelAPI.retrofit }
}