package welias.marvel.domain.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import welias.marvel.domain.usecase.GetCharactersUseCase

private val useCaseModule = module {
    factory { GetCharactersUseCase(repository = get()) }
}

object DomainModule {
    fun load() = loadKoinModules(useCaseModule)
}
