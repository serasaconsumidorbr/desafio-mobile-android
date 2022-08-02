package com.developer.marvel.infrastructure.datasources

import com.developer.marvel.infrastructure.dto.CharacterDto
import com.developer.marvel.infrastructure.exceptions.*

interface CharacterDataSource {

    @Throws(
        GenericException::class,
        MissingParameterException::class,
        MissingHashException::class,
        MissingTimestampException::class,
        MissingAPIKeyException::class,
        InvalidRefererException::class,
        InvalidHashException::class,
        InvalidCredentialsException::class,
        MethodNotAllowedException::class,
        ForbiddenException::class
    )
    suspend fun getCharacters(page: Int, limit: Int): List<CharacterDto>
}