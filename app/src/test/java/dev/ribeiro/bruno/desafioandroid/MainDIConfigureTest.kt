package dev.ribeiro.bruno.desafioandroid

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

const val BASE_URL_MARVEL = "http://gateway.marvel.com/v1/public/"

fun configureTestAppComponent() = startKoin {
    loadKoinModules(
        configureDataModuleForTest(BASE_URL_MARVEL) + configureModuleDomainForTest()
    )
}