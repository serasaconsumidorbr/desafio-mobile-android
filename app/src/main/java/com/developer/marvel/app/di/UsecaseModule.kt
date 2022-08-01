package com.developer.marvel.app.di

import com.developer.marvel.domain.usecases.CharacterUseCases
import org.koin.dsl.module

val usecasesModule = module {
    single { CharacterUseCases(repository = get()) }
}