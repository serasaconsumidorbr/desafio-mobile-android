package welias.marvel

import android.app.Application
import org.koin.android.ext.koin.androidContext
import welias.marvel.data.di.DataModule
import welias.marvel.data.provider.ContextProvider
import welias.marvel.domain.di.DomainModule
import welias.marvel.presentation.di.PresentationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupProvider()
        setupKoin()
    }

    private fun setupProvider() {
        ContextProvider.initialContext(this.applicationContext)
    }

    private fun setupKoin() {
        startKoin()
        loadModules()
    }

    private fun loadModules() {
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
        }
    }
}
