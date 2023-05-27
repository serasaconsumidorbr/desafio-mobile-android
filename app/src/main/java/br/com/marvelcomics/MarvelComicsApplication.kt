package br.com.marvelcomics

import android.app.Application
import android.widget.Toast
import br.com.marvelcomics.di.AppModule
import br.com.marvelcomics.di.DatabaseModule
import br.com.marvelcomics.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelComicsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Apenas uma maneira de notificar que as chaves não foram definidas
        if (checkForApiKey()) {
            Toast.makeText(
                this,
                "Informe as chaves da api no local.properties",
                Toast.LENGTH_LONG
            ).show()
        }
        startKoin {
            androidContext(this@MarvelComicsApplication)
            modules(AppModule.dependencies, NetworkModule.dependencies, DatabaseModule.dependencies)
        }
    }

    private fun checkForApiKey(): Boolean =
        BuildConfig.API_KEY == "null" || BuildConfig.API_KEY.isEmpty() ||
                BuildConfig.HASH_KEY == "null" || BuildConfig.HASH_KEY.isEmpty() ||
                BuildConfig.TIMESTAMP_KEY == "null" || BuildConfig.TIMESTAMP_KEY.isEmpty()

}