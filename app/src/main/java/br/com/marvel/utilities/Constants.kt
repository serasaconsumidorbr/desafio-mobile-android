package br.com.marvel.utilities

import br.com.marvel.BuildConfig

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        const val PUBLIC_KEY = BuildConfig.PUBKEY
        const val PRIVATE_KEY = BuildConfig.PRIVKEY
    }
}