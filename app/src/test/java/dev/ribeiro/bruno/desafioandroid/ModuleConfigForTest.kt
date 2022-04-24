package dev.ribeiro.bruno.desafioandroid

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dev.ribeiro.bruno.desafioandroid.data.repository.MarvelRepositoryImpl
import dev.ribeiro.bruno.desafioandroid.data.service.MarvelService
import dev.ribeiro.bruno.desafioandroid.domain.repository.MarvelRepository
import dev.ribeiro.bruno.desafioandroid.domain.usecase.GetAllCharactersUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

fun configureModuleDomainForTest() = module {
    factory{ GetAllCharactersUseCase(get()) }
}

fun configureDataModuleForTest(base_url: String) = module {

    single {
        val factory = createGsonBuilder()
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .build()
            .create(MarvelService::class.java)
    }

    single<MarvelRepository> {
        MarvelRepositoryImpl(get())
    }

}

private fun createGsonBuilder(): Gson {
    return GsonBuilder().apply {
        setLenient()
        registerTypeAdapter(
            Date::class.java,
            JsonDeserializer<Date> { json, typeOfT, context ->
                if (json.asJsonPrimitive.isNumber) {
                    Date(json.asJsonPrimitive.asLong * 1000)
                } else {
                    null
                }
            })
    }.create()
}
