package com.example.testeapp.data.repositores

import com.example.testeapp.model.*
import retrofit2.Response
import retrofit2.Retrofit

interface RemoteRepository {

    suspend fun fetchCharacters(offSet: Int): Result<ListResponse>
    suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T>
    fun parseError(response: Response<*>, retrofit: Retrofit): Error?

}