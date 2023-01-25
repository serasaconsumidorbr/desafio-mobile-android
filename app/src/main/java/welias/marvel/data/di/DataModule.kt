package welias.marvel.data.di

import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import welias.marvel.BuildConfig
import welias.marvel.core.constants.DEFAULT_DISPATCHER
import welias.marvel.core.constants.DEFAULT_SCOPE
import welias.marvel.core.constants.IO_DISPATCHER
import welias.marvel.core.constants.MAIN_DISPATCHER
import welias.marvel.data.datasource.remote.AppRemoteDataSource
import welias.marvel.data.datasource.remote.AppRemoteDataSourceImpl
import welias.marvel.data.provider.ApiFactory
import welias.marvel.data.provider.NetworkProvider
import welias.marvel.data.provider.OkHttpClientFactory
import welias.marvel.data.provider.RetrofitFactory
import welias.marvel.data.repository.AppRepositoryImpl
import welias.marvel.data.service.AppService
import welias.marvel.domain.repository.AppRepository

val apiModule = module {
    factory { ApiFactory.build(retrofit = get(), apiClass = AppService::class.java) }
}

val networkModule = module {
    factory {
        OkHttpClientFactory.build()
    }

    factory<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    factory {
        RetrofitFactory.build(url = BuildConfig.BASE_URL, client = get(), factory = get())
    }

    single { NetworkProvider() }
}

val repositoryModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(
            remoteDataSource = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}

val dataSourceModule = module {
    factory<AppRemoteDataSource> {
        AppRemoteDataSourceImpl(
            service = get()
        )
    }
}

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

object DataModule {
    fun load() = loadKoinModules(
        networkModule + apiModule + repositoryModule + dataSourceModule + dispatcherModule
    )
}
