package dev.ribeiro.bruno.desafioandroid.domain.di

import dev.ribeiro.bruno.desafioandroid.domain.usecase.GetAllCharactersUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            single { GetAllCharactersUseCase(get()) }
        }
    }

}