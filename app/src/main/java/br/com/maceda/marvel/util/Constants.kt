package br.com.maceda.marvel.util

import br.com.maceda.marvel.BuildConfig

object Constants {

    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    const val PUBLIC_KEY = BuildConfig.API_PUBLIC_KEY
    const val PRIVATE_KEY = BuildConfig.API_PRIVATE_KEY

    object Params {
        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val TS = "ts"
    }
}