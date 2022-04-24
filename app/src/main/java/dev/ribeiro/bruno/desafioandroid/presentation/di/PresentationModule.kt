package dev.ribeiro.bruno.desafioandroid.presentation.di

import dev.ribeiro.bruno.desafioandroid.presentation.viewmodel.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(
            viewModelModule()
        )
    }

    private fun viewModelModule() : Module{
        return module {
            factory { HomeViewModel(get()) }
        }
    }

}