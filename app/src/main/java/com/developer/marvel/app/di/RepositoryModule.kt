package com.developer.marvel.app.di

import com.developer.marvel.domain.repositories.CharacterRepository
import com.developer.marvel.infrastructure.repositories.CharacterRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(dataSource = get()) }
}