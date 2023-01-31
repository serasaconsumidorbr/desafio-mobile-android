package welias.marvel.data.provider

import retrofit2.Retrofit

object ApiFactory {
    fun <T> build(retrofit: Retrofit, apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}