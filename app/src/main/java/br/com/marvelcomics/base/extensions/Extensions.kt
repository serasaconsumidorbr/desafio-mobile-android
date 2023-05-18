package br.com.marvelcomics.base.extensions

import org.koin.core.scope.Scope
import retrofit2.Retrofit

inline fun <reified T> Scope.resolveRetrofit(retrofit: Retrofit) : T {
    return retrofit.create(T::class.java)
}