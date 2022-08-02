package com.developer.marvel.external.datasources.helper

import com.developer.marvel.infrastructure.dto.service.ErrorApiDto
import com.developer.marvel.infrastructure.exceptions.*
import okhttp3.ResponseBody

class ResponseError(
    private val statusCode: Int,
    errorBody: ResponseBody?
) {

    private val error = ErrorApiDto.parse(errorBody?.string())

    fun getCode(): String = error.code

    fun getMessage(): String = error.message

    fun getException(): MarvelExceptions {
        return when (statusCode) {
            409 -> when (error.code) {
                "MissingParameter" -> MissingParameterException(error.message)
                "Missing Hash" -> MissingHashException(error.message)
                "Missing Timestamp" -> MissingTimestampException(error.message)
                "Missing API Key" -> MissingAPIKeyException(error.message)
                else -> GenericException()
            }

            401 -> when (error.code) {
                "Invalid Referer" -> InvalidRefererException(error.message)
                "Invalid Hash" -> InvalidHashException(error.message)
                "InvalidCredentials" -> InvalidCredentialsException(error.message)
                else -> GenericException()
            }

            405 -> MethodNotAllowedException(error.message)

            403 -> ForbiddenException(error.message)

            else -> GenericException()
        }
    }
}