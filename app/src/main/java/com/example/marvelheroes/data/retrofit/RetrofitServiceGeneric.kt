package com.example.marvelheroes.data.retrofit

import retrofit2.HttpException

abstract class RetrofitServiceGeneric<in PARAM, out RESPONSE> {
    protected abstract suspend fun execute(param: PARAM): RESPONSE

    open suspend operator fun invoke(value: PARAM) =
        executor {
            try {
                ResultWrapper.Success(execute(value))
            } catch (http: HttpException) {
                ResultWrapper.Failure(
                    code = http.code(),
                    error = ErrorObject(http),
                    throwable = http
                )
            } catch (errorObjectException: ErrorObjectException) {
                ResultWrapper.Failure(
                    error = errorObjectException.error,
                    throwable = errorObjectException
                )
            } catch (throwable: Throwable) {
                ResultWrapper.Failure(error = ErrorObject(), throwable = throwable)
            }
        }
}