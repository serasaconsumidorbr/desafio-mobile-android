package com.developer.marvel.app.di

import com.developer.marvel.domain.repositories.CharacterRepository
import com.developer.marvel.external.datasources.CharacterDataSourceImpl
import com.developer.marvel.infrastructure.datasources.CharacterDataSource
import org.koin.dsl.module

val datasourceModule = module {
    single<CharacterDataSource> { CharacterDataSourceImpl(retrofit = get()) }
}