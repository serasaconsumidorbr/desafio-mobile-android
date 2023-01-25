package welias.marvel.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import welias.marvel.presentation.ui.fragments.home.HomeViewModel

val viewModelModule = module {
    viewModel { HomeViewModel(useCase = get()) }
}

object PresentationModule {
    fun load() = loadKoinModules(viewModelModule)
}
